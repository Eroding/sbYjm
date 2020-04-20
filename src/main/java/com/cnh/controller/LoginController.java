package com.cnh.controller;


import com.cnh.Vo.LoginVo;
import com.cnh.dataobject.User;
import com.cnh.enums.ResultEnum;
import com.cnh.exception.SellException;
import com.cnh.result.Result;
import com.cnh.service.UserService;
import com.cnh.util.ResultUtil;
import com.cnh.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;



    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }


    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin( HttpServletResponse response, @RequestBody @Valid LoginVo loginVo,
                                    BindingResult bindingResult){
        log.info(loginVo.toString());
        //参数校验
        if(bindingResult.hasErrors()){
            //校验器，校验的定义在传进来的参数里，和以前的不一样，详情看factory项目
            log.error("[创建订单] 参数不正确，loginVo={}",loginVo);
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
      //这里要校验，但是这个是做一个校验器，没有实现真正的校验
  /*  if(!ValidatorUtil.isMobile(loginVo.getMobile())){
       throw new SellException(ResultEnum.NUMBER_ERROR);
  }*/
//如果没有问题，就开始登陆
        User user =  userService.login(response,loginVo);
return ResultUtil.success(user);

    }


    @RequestMapping("/accuss")
    @ResponseBody
    public Result<Boolean> accuss( HttpServletResponse response,  @RequestBody @Valid LoginVo loginVo,
                                    BindingResult bindingResult){
        log.info(loginVo.toString());
        //参数校验
        if(bindingResult.hasErrors()){
            //校验器，校验的定义在传进来的参数里，和以前的不一样，详情看factory项目
            log.error("[登录] 参数不正确，loginVo={}",loginVo);
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //这里要校验，但是这个是做一个校验器，没有实现真正的校验
  /*  if(!ValidatorUtil.isMobile(loginVo.getMobile())){
       throw new SellException(ResultEnum.NUMBER_ERROR);
  }*/
//如果没有问题，就开始注册
        userService.access(response,loginVo);
        return ResultUtil.success(true);

    }
}
