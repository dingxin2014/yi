package com.fateking.yi.po;

import com.fateking.yi.enums.Symbol;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "T_YI_KTICK")
@Data
public class KTickPO {

    @Enumerated(EnumType.STRING)
    private Symbol symbol;
    @Id
    @GeneratedValue
    private Long id;
    private Long klineId;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal vol;
    private Long count;


}
