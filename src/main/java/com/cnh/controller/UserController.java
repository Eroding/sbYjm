package com.cnh.controller;


import com.cnh.dataobject.User;
import com.cnh.enums.ResultEnum;
import com.cnh.service.UserService;
import com.cnh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //admin用户查看其它所有会员信息
    @RequestMapping("/lookByAdmin")
    public Object lookByAdmin(String nickname) {
        if(!nickname.equals("admin")){
return ResultUtil.error(ResultEnum.NOT_ADMIN);
        }
       List<User> user= userService.lookByAdmin();
        return ResultUtil.success(user);
    }

    //admin用户查看自己信息
    @RequestMapping("/lookBySelef")
    public Object lookBySelef(int id) {
      User user=  userService.lookBySelef(id);
        return ResultUtil.success(user);
    }


    //admin用户更新信息
    @RequestMapping("/update")
    public Object update(@RequestBody User user) {
        userService.update(user);
        return ResultUtil.success();
    }

    //admin认证用户信息,是否认证成功
    @RequestMapping("/relam")
    public Object relam(int userId,int auth) {
        User user =userService.getById(userId);
        user.setAuth(auth);
        userService.update(user);
        return ResultUtil.success();
    }


    //用户提交认证信息
    @RequestMapping("/updateRelam")
    public Object updateRelam(@RequestBody User user) {
        user.setAuth(1);
userService.update(user);
        userService.update(user);
        return ResultUtil.success();
    }

    //admin用户注销
    @RequestMapping("/delete")
    public Object delete(int id) {
        userService.delete(id);
        return ResultUtil.success();
    }

}
