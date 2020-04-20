package com.cnh.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作为手机号校验工具
 */
public class ValidatorUtil {

    //1 \\d 表示数字 {10} 10位数字
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
//像这种经常传进来某个参数，需要先判断这个是否为空，最好就是用StringUtils.isEmpty
if(StringUtils.isEmpty(src)){
    //像这种return false；这个程序后来怎么跑
    return false;
}
Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

}
