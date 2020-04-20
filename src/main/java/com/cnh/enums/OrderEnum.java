package com.cnh.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum OrderEnum {

    /**
     * 就是订单的状态有好多种。
     */
    ORDER_NOPAY(10001,"订单未支付"),

    ORDER_ASCEPTSENDGOODS(10002,"待发货"),

    ORDER_SENDGOODS(10003,"已发货"),

    ORDER_GETGOODS(10004,"已收货"),

    ORDER_BACKMONEY(10005,"已退款"),

    ORDER_FINISH(10006,"已完成"),

    ORDER_NOT_EXIT(10007,"订单不存在"),
    ;


    private int code;

    private String message;


    OrderEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
