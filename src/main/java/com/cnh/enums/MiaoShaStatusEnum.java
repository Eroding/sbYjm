package com.cnh.enums;


import lombok.Getter;

@Getter
public enum MiaoShaStatusEnum {

    UN_START(0,"还没开始，请等待"),

    FINISH(1,"已结结束了，敬请期待下次哦"),

    STARTING(2,"活动正在进行中"),
    COLLECTION(7,"您已收藏该手机，请勿重复收藏"),

STOCK_EMPTY(3,"商品库存不足,已经被秒杀抢光"),
    //重复秒杀
    REPEATE_MIAOSHA(4,"商品重复秒杀"),

    UN_ALLOW(5,"非法请求"),

    VERIFYCODE_ERROR(6,"验证码不通过"),
    ;





    private int code;

    private String message;

    MiaoShaStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
