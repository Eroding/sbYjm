package com.cnh.exception;

import com.cnh.enums.ResultEnum;
import lombok.Data;

@Data
public class Exceptions extends RuntimeException {
    private Integer code;

    public Exceptions(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code =resultEnum.getCode();

    }
}
