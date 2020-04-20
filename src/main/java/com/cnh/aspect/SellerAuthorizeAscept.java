package com.cnh.aspect;


import com.cnh.dataobject.User;
import com.cnh.dataobject.UserContext;
import com.cnh.exception.SellerAuthorizeException;
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * 使用切面需要用jar包
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAscept {

    @Autowired
    private RedisService redisService;

    /**
     * 假设定义现在就切这个订单controller，查看这个redis是否含有token，token在redis是否有值
     * 有值就可以查看，如果没有值，就让他返回主页面登录在进来
     */
    @Pointcut("execution(public * com.cnh.controller.DemoController.*(..)))"
    )
    public void verify(){

    }
    @Before("verify()")
    public void doverify() {

        //以前是把id存放到redis中，所以使用的是StringTemplate存放，但是这次是
        //使用jedis操作这个redis，用来存放一个对象，

        //本来在controller层先查看request中有没有带回来一个参数名会 TOKEN的值，
        //但是在列表查询和好多下单操作都需要查看，变得比较麻烦，不如做一个切面，
        //在访问这个接口的时候先查看是否有token


        /**
         * 先拿到request
         */
        User user = UserContext.getUser();


    }
    }

