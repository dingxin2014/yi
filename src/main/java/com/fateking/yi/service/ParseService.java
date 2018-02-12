package com.fateking.yi.service;

import com.fateking.yi.po.ParsePO;

public interface ParseService {
    ParsePO save(ParsePO parsePO);

    boolean exist(ParsePO parsePO);
}
