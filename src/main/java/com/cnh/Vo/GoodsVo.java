package com.cnh.Vo;

import com.cnh.dataobject.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class GoodsVo extends Goods {



    private Date startDate;

    private Date endTime;

    private Integer miaoshaStock;

    private Double miaoshaPrice;
}
