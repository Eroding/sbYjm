package com.cnh.redis;

public class ListKey extends BasePrefix {

    //设置这个token，也就是key
    //public  static final String COOKIE_NAME_TOKEN="goodslist";

    //设置token的有效期,两天
    public static final int TOKEN_EXPIRE =60;

    public ListKey(String prefix) {
        super(prefix);
    }

    public ListKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static ListKey listKey = new ListKey(TOKEN_EXPIRE,"goods");

    public static ListKey getMiaoshaGoodsStock = new ListKey(0,"goodsStock");
}
