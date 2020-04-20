package com.cnh.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来从request中获取cookie
 * 在response中存放cookie
 */
public class CookieUtil {

    //设置前端的cookie
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(name,value);

        //设置这个cookie是一个全局配置，任何路径都可以访问到
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    //获取cookie
    public static   Cookie get(HttpServletRequest request, String name){

Map<String,Cookie> cookieMap = readCookieMap(request);

//如果输入进来的name和cookieMap取出来的值一样，就得到该key的value
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else
            return null;
    }

//可能会从前端很多cookie，不能确定，而且传过来的是一个数组，我们需要转化为一个集合
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request) {

        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);

            }
            System.out.println("此时的cookieMap"+cookieMap.get("token"));
        }
        return cookieMap;
    }
}
