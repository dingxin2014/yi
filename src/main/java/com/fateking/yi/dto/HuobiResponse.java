package com.fateking.yi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HuobiResponse<T, D> implements Serializable {

    private String status;  //状态
    private Long ts;    //耗时
    private T tick;
    private String ch;  //通道

    private D data;

    @JsonProperty("err-code")
    private String errCode;
    @JsonProperty("err-msg")
    private String errMsg;

}
