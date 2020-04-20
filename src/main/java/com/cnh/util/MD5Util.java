package com.cnh.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

public static String md5(String src){
return DigestUtils.md5Hex(src);
}

private static final String salt="1a2b3c4d";


    /**
     * 将传过来的密码加固定的盐
     * @param inputPass
     * @return
     */
    public static String inputPassFromPass(String inputPass){
        //从盐中随便去某个下标的值，和传进来的值相加
   String str = ""+salt.charAt(0)+inputPass+salt.charAt(3)+salt.charAt(5);
   return md5(str);
}

//当前端传过来的第一层加密的数据在进行加密，根据动态salt
    public static String formPassToDb(String formPass,String salt){
        //从盐中随便去某个下标的值，和传进来的值相加
        String str = ""+salt.charAt(0)+formPass+salt.charAt(3)+salt.charAt(5);
        return md5(str);
    }

//前端到，一次性的数据库
    public static String inputToDbPass(String input,String saltDb){
       String formPass = inputPassFromPass(input);
      String dbpass=  formPassToDb(formPass,saltDb);
return dbpass;

    }

    public static void main(String[] args) {
        System.out.println(inputPassFromPass("123456"));
    }

}
