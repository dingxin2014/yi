package com.fateking.yi.po;

import com.fateking.yi.enums.Symbol;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_YI_ORDER")
@Data
public class OrderPO {

    @Enumerated(EnumType.STRING)
    private Symbol symbol;
    @Id
    @GeneratedValue
    private Long id;

}

