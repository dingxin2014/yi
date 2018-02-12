package com.fateking.yi.support;

import com.fateking.yi.dto.KTick;
import com.fateking.yi.exception.IllegalArgumentException;
import com.fateking.yi.service.KTickService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * K 线栈
 *
 * @author dingxin
 */
public class KTickStack implements Iterable<KTick> {

    private Map<Long, KTick> data = Maps.newConcurrentMap();

    private int size;
    private int interval;

    private Long topId;
    private Long bottomId;

    private transient boolean init = false;

    private KTickService kTickService;

    public KTick get(int index) {
        return data.get(bottomId + interval * index);
    }

    public List<KTick> values() {
        List<KTick> values = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            values.add(get(i));
        }
        return values;
    }

    public List<KTick> recent(int size) {
        if (size >= this.size) {
            throw new ArrayIndexOutOfBoundsException("Illegal size " + size + "with " + size + "size.");
        }
        List<KTick> values = Lists.newArrayList();
        for (int i = this.size - 1; i >= this.size - size; i--) {
            values.add(get(i));
        }
        return values;
    }

    public int size() {
        return size;
    }

    public KTick getTop() {
        return reverseGet(0);
    }

    public KTick reverseGet(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Illegal index " + index + "with " + size + "size.");
        }
        return data.get(topId - index * interval);
    }

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

        if (data.containsKey(max)) {
            return;
        }

        if (!init) {
            init = true;
        }

        if ((max - min) % interval != 0) {
            throw new IllegalArgumentException("Illegal KLine list!");
        }

        Long p;
        for (p = init ? min : topId; p <= max; p = p + interval) {
            KTick tmp = map.get(p);
            data.put(p, tmp);
            kTickService.asyncCheckAndSave(tmp);
        }


        topId = max;
        bottomId = max - (size - 1) * interval;

        data.forEach((key, value) -> {
            if (key < bottomId) {
                data.remove(key);
            }
        });

    }


    @Override
    public Iterator<KTick> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<KTick> {

        int cursor;       // index of next element to return

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public KTick next() {
            cursor++;
            return get(cursor - 1);
        }
    }
}
