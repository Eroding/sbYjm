package com.cnh.Vo;

import com.cnh.validator.IsMobile;
import lombok.Data;
/**
 * 这里都是用原先的校验代码，可以自定义校验规则，比如传进来的数组中不能含空值之类的，
 */
import javax.validation.constraints.NotEmpty;

@Data
public class LoginVo {

    @NotEmpty(message = "用户名必填")
    //@IsMobile
    private String nickname;
    @NotEmpty(message = "密码必填")
    private String password;

    @Override
    public String toString() {
        return "LoginVo{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
