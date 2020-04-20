package com.cnh.dataobject;

import lombok.Data;

@Data
public class OrderUserPhone {

    private String nickname; //用户名
    private String id;  //订单id
    private String phoneId; //手机主键
    private String userId; //用户主键
    private String orderStstus; //订单状态
    private String comments;  //评论
    private String phonename;  //手机名称
    private String imgUrl;  //手机图片
    private String price; //价格
    private String phoneStatus;  //0新手机 1 旧手机
    private String type; //型号
    private String store;  //库存
    private String cont;  //描述
}
