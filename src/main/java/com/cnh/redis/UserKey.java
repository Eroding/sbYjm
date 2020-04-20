package com.cnh.redis;

public class UserKey extends BasePrefix {

//设置这个token，也就是key
    public  static final String COOKIE_NAME_TOKEN="token";

    //设置token的有效期,两天
    public static final int TOKEN_EXPIRE =3600 * 24 * 2;


    public UserKey(int expireSeconds,String prefix) {

        super(expireSeconds,prefix);
    }

    //public static UserKey getById  = new UserKey(TOKEN_EXPIRE,"id");

   // public static UserKey getByName = new UserKey(TOKEN_EXPIRE,"name");

    public static UserKey token = new UserKey(TOKEN_EXPIRE,"tk");

//当根据id来查找用户时，可以把查出看来的user放到redis中，设置为0表示永久有效期
    public static UserKey getById = new UserKey(0,"id");



}
