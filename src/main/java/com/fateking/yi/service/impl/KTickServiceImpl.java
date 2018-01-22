package com.fateking.yi.service.impl;

import com.fateking.yi.dto.KTick;
import com.fateking.yi.po.KTickPO;
import com.fateking.yi.repository.KTickRepository;
import com.fateking.yi.service.KTickService;
import com.fateking.yi.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.fateking.yi.utils.Assert.notNull;

@Service
@Transactional
public class KTickServiceImpl implements KTickService {

    @Autowired
    KTickRepository kTickRepository;

    @Override
    public KTickPO save(KTickPO kTickPO) {
        notNull(kTickPO, "kTickPO must not be null!");
        return kTickRepository.save(kTickPO);
    }

    @Override
    public KTickPO findByKlineId(Long klineId) {
        return kTickRepository.findByKlineId(klineId);
    }

    @Override
    public KTickPO checkAndSave(KTickPO kTickPO) {
        KTickPO origin;
        if ((origin = findByKlineId(kTickPO.getKlineId())) == null) {
            return save(kTickPO);
        }
        return origin;
    }

    @Override
    @Async
    public KTickPO asyncCheckAndSave(KTick kTick) {
        return checkAndSave(BeanUtils.convert(kTick, KTickPO.class, po -> {
            po.setKlineId(kTick.getId());
            return po;
        }, "id"));
    }
}
