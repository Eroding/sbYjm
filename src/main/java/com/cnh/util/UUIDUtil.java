package com.cnh.util;

import java.util.UUID;

/*
用来生成token，也就是cookie的key
 */
public class UUIDUtil {

    public static String uuid(){
return UUID.randomUUID().toString().replace("-","");
    }
}
