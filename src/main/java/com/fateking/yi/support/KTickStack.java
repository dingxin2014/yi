package com.fateking.yi.support;

import com.fateking.yi.dto.KTick;
import com.fateking.yi.exception.IllegalArgumentException;
import com.fateking.yi.service.KTickService;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * K 线栈
 *
 * @author dingxin
 */
public class KTickStack {

    private Map<Long, KTick> data = Maps.newConcurrentMap();

    private int size;
    private int interval;

    private Long topId;
    private Long bottomId;

    private KTickService kTickService;

    public KTickStack(int size, int interval) {
        if (size == 0 || interval == 0) {
            throw new IllegalArgumentException("非法的size或interval!");
        }
        this.size = size;
        this.interval = interval;
        this.kTickService = SpringObjectFactory.getBean(KTickService.class);
    }

    public void parse() {
        if (data.isEmpty()) {
            return;
        }
    }

    public void parseKList(List<KTick> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list = list.stream().sorted(Comparator.comparing(KTick::getId)).collect(Collectors.toList());
        Map<Long, KTick> map = list.stream().collect(Collectors.toMap(KTick::getId, Function.identity()));
        KTick maxKTick = list.get(list.size() - 1);
        KTick minKTick = list.get(0);
        Long max = maxKTick.getId();
        Long min = minKTick.getId();

        Long minId = max - (size - 1) * interval;

        if ((max - min) % interval != 0) {
            throw new IllegalArgumentException("Illegal KLine list!");
        }

        boolean isInit = false;
        if (topId == null || bottomId == null) {
            topId = max;
            bottomId = min;
            isInit = true;
        }

        if (data.containsKey(max)) {
            return;
        }

        Long p;
        for (p = isInit ? min : topId; p <= max; p = p + interval) {
            KTick tmp = map.get(p);
            data.put(p, tmp);
            kTickService.asyncCheckAndSave(tmp);
        }

        data.forEach((key, value) -> {
            if (key < minId) {
                data.remove(key);
            }
        });

    }


}
