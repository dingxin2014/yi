package com.fateking.yi.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class HuobiStandardResponse<T> implements Serializable {

    private String status;
    private T data;

}
