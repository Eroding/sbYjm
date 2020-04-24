package com.cnh.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 身份证识别返回的实体
 */
@Data
public class IdCardDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String address; //住址
    private String birth; //出生
    private String name; //姓名
    private String id; //身份证
    private String sex; //性别
    private String nation; //民族
}
