package com.fateking.yi.service.impl;

import com.fateking.yi.po.ParsePO;
import com.fateking.yi.repository.ParseRepository;
import com.fateking.yi.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fateking.yi.utils.Assert.notNull;

@Service
public class ParseServiceImpl implements ParseService {


    @Autowired
    private ParseRepository parseRepository;

    @Override
    public ParsePO save(ParsePO parsePO) {
        notNull(parsePO, "ParsePO must not be null!");
        return parseRepository.save(parsePO);
    }

    @Override
    public boolean exist(ParsePO parsePO) {
        notNull(parsePO, "ParsePO must not be null!");
        return parseRepository.findByTimestampAndSymbol(parsePO.getTimestamp(), parsePO.getSymbol()) != null;
    }

}
