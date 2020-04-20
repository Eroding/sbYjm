package com.cnh.access;

import com.alibaba.fastjson.JSON;
import com.cnh.dataobject.User;
import com.cnh.dataobject.UserContext;
import com.cnh.enums.MiaoShaStatusEnum;
import com.cnh.enums.ResultEnum;
import com.cnh.exception.SellException;
import com.cnh.exception.SellerAuthorizeException;
import com.cnh.redis.MiaoshaKey;
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.util.CookieUtil;
import com.cnh.util.ResultUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 拦截器
 */
@Slf4j
@Service
public class AccessLimitInterceptor extends HandlerInterceptorAdapter  {

/*
    @Autowired
    RedisService redisService;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

   if(handler instanceof HandlerMethod){
       //先取用户,
       User user =  getUser();
 //获取到user之后，先保存起来
       UserContext.setUser(user);




       //强转
       HandlerMethod hm = (HandlerMethod)handler;
       //拿到去方法时得到方法的注解
     AccessLimit accessLimit=  hm.getMethodAnnotation(AccessLimit.class);
     if(accessLimit ==null){
         return true;


     }
      int seconds =  accessLimit.seconds();
     int maxCount =  accessLimit.maxCount();
      boolean needLogin= accessLimit.needLogin();


      String key =request.getRequestURI();
if(needLogin){
    if(user ==null){
        render(response, ResultEnum.USER_UNLOGIN);
        return false;
    }
    key+="_"+user.getId();
}else {

}
MiaoshaKey ak = MiaoshaKey.withTimes(seconds);


       Integer count = redisService.get(ak,key,Integer.class);
       if(count ==null){
           redisService.set(ak,key,1);
       }else if(count < maxCount){
           redisService.incr(ak,key);
       }else {
           render(response,ResultEnum.ACCESS_TOKEN_MORE);
           return false ;
       }

   }
        return true;
    }

    private void render(HttpServletResponse response, ResultEnum userUnlogin) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        String str = JSON.toJSONString(ResultUtil.error(userUnlogin));
        outputStream.write(str.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }


    //先得到用户，这个用户从url里面取
    private User getUser(){

        */
/**
         * 先拿到request
         *//*

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie，
        Cookie cookie= CookieUtil.get(request, UserKey.COOKIE_NAME_TOKEN);
        //先查看有没有这个cookie
        if(cookie ==null){
            log.warn("[登录校验] Cookie中查不到token");
            throw new SellerAuthorizeException();
        }else{
            log.info("cookie=",JSON.toJSON(cookie).toString());
        }
//如果有cookie再查看有没有这个cookie所存放在redis中的内容
        String key = cookie.getValue();
        User user = redisService.get(UserKey.token,key, User.class);
        if(user ==null){
            log.warn("[登录校验] Redis中查不到token");
            throw new SellerAuthorizeException();
        }

        return user;
    }
*/






}
