package com.cnh.Vo;

import com.cnh.dataobject.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo orderInfo;


}
