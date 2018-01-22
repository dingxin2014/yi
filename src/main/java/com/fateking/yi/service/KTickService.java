package com.fateking.yi.service;

import com.fateking.yi.dto.KTick;
import com.fateking.yi.po.KTickPO;
import org.springframework.scheduling.annotation.Async;

public interface KTickService {
    KTickPO save(KTickPO kTickPO);

    KTickPO findByKlineId(Long klineId);

    KTickPO checkAndSave(KTickPO kTickPO);

    @Async
    KTickPO asyncCheckAndSave(KTick kTick);
}
