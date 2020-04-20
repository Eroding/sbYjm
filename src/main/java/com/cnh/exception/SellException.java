package com.cnh.exception;

import com.cnh.enums.MiaoShaStatusEnum;
import com.cnh.enums.OrderEnum;
import com.cnh.enums.ResultEnum;
import lombok.Data;

@Data
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code =resultEnum.getCode();

    }

 public  SellException(Integer code,String message){
        super(message);
        this.code =code;
 }

    public SellException(OrderEnum orderEnum){
        super(orderEnum.getMessage());
        this.code =orderEnum.getCode();

    }


    public SellException(MiaoShaStatusEnum miaoshaEnum){
        super(miaoshaEnum.getMessage());
        this.code =miaoshaEnum.getCode();

    }

}
