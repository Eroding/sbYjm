package com.cnh.redis;

/**
 * 打个比方，就是有order页面所需要存到redis，就这样写
 */
public class OrderKey extends BasePrefix {





    public OrderKey(int expireSeconds, String prefix) {

        super(expireSeconds, prefix);
    }


    //当根据uid和goodsid来miaoshaorder，
    public static OrderKey getMiaoshaOrderByUidGoodsId = new OrderKey(0,"uIdgoodsId");

}
