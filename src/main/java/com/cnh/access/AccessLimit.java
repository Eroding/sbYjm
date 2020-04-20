package com.cnh.access;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来限流,自定义的注解
 */

@Target({ElementType.METHOD})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface AccessLimit {
 int seconds();
 int maxCount();
 boolean needLogin() default true;



}
