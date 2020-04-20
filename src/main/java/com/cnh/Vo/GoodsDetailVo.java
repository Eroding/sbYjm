package com.cnh.Vo;


import com.cnh.dataobject.User;
import lombok.Data;

@Data
public class GoodsDetailVo {

    private int miaoshaStatus =0;

    private int remainSeconds =0;

    private  GoodsVo goods;

private User user;


}
