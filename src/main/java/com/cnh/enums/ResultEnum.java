package com.cnh.enums;

import com.cnh.dto.CodeMessage;
import lombok.Getter;

/**
 * 因为这属于数据字典，。所以 不需要set方法，因为set的话是可以让被人设置了
 *
 */



@Getter
public enum ResultEnum  {

    FAIL(-1,"未知异常。可能是系统崩溃"),

SUCCESS(0,"success"),

INTERNET_ERROR(1,"网络传输错误"),

 INTERNET_EXCEPTION(2,"网络异常"),

    PARAM_ERROR(3,"参数不正确"),

   NUMBER_EMPTY(4,"手机号为空"),

    PASSWORD_EMPTY(5,"密码为空"),

    PASSWORD(6,"密码错误，请重新输入"),

    NUMBER_ERROR(7,"手机号格式错误"),

    USER_EMPTY(8,"用户不存在"),

    USER_HAS(10,"用户已存在"),
    USER_UNLOGIN(9,"用户没有登录"),

    NOT_ADMIN(9,"您并不是管理员，没有权限查看"),

    ACCESS_TOKEN_MORE(7,"访问次数太过频繁，请稍等一会");

    ;


private int code;

private String message;


    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
