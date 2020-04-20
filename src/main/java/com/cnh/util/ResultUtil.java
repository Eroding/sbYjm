package com.cnh.util;



import com.cnh.enums.MiaoShaStatusEnum;
import com.cnh.enums.ResultEnum;
import com.cnh.result.Result;

/**
 * 返回的接口封装
 */
public class ResultUtil {

    public static Result success(Object object){

        Result result  = new Result();
        result.setData(object);
        result.setCode(0);
        result.setMsg("success");
return result;
    }


    public static Result success(){

        Result result  = new Result();

        result.setCode(0);
        result.setMsg("success");
        return result;
    }


    public static Result error(Integer code,String message){
        Result result  = new Result();
        result.setMsg(message);
        result.setCode(code);
        return result;
    }

    public static Result error(ResultEnum resultEnum){
        Result result  = new Result();
        result.setMsg(resultEnum.getMessage());
        result.setCode(resultEnum.getCode());
        return result;
    }

    public static Result error(MiaoShaStatusEnum miaoshaEnum){
        Result result  = new Result();
        result.setMsg(miaoshaEnum.getMessage());
        result.setCode(miaoshaEnum.getCode());
        return result;
    }
}
