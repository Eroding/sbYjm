package com.cnh.exception;

/**
 * 登录校验发生错误
 */
public class SellerAuthorizeException extends RuntimeException {

    //这里就不写构造方法了，因为其他都是以构造方法，让handler捕捉到，然后根据信息返回

    //这个异常发生时，就直接让handler捕捉并且返回到登录页面，这个是在详情列表，或者订单查询

    //因为redis/cookie中可能没有该对应的值或者是过期了，所以在handler中设置的是modelAndView

    //不用着急定义的返回接口了
}
