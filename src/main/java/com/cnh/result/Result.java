package com.cnh.result;

import lombok.Data;

import java.io.Serializable;

/**
 *  一开始创建项目的时候就加载了lombok
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

}
