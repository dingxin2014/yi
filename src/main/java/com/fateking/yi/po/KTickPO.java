package com.fateking.yi.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "T_YI_KTICK")
@Data
public class KTickPO {

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
