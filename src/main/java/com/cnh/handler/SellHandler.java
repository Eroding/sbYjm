package com.cnh.handler;


import com.cnh.enums.ResultEnum;
import com.cnh.exception.Exceptions;
import com.cnh.exception.SellException;
import com.cnh.exception.SellerAuthorizeException;
import com.cnh.result.Result;
import com.cnh.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理controller层抛出的异常，advice就是切面的意思
 */
@ControllerAdvice
@ResponseBody
public class SellHandler {


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public Result handlerSellerException(SellException sellException){
        return ResultUtil.error(sellException.getCode(),sellException.getMessage());
    }


    @ExceptionHandler(value = Exceptions.class)
    @ResponseBody
    public Result handlerException(Exceptions Exceptions){

        return ResultUtil.error(ResultEnum.FAIL);
    }



    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseBody
    public ModelAndView handlerSellerAuthorizeException(SellerAuthorizeException sellerAuthorizeException){

        return new ModelAndView("redirect:/login.html");
    }


}
