package com.fateking.yi.repository;

import com.fateking.yi.po.KTickPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KTickRepository extends JpaRepository<KTickPO, Long> {

    KTickPO findByKlineId(Long klineId);

}
