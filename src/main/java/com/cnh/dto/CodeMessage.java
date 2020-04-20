package com.cnh.dto;


import lombok.Data;

/**
 * 把code和mesg放一个类里面
 */

@Data
public class CodeMessage {

    /**
     * 发现这个玩意好像没什么用，我直接把enum传到util方法
     * @param code
     * @param message
     */


    public CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;


}
