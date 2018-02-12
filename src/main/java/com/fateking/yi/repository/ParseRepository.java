package com.fateking.yi.repository;

import com.fateking.yi.enums.Symbol;
import com.fateking.yi.po.ParsePO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParseRepository extends JpaRepository<ParsePO, Long> {

    ParsePO findByTimestampAndSymbol(Long timestamp, Symbol symbol);
}
