package com.cnh.service;

import com.cnh.Vo.GoodsVo;
import com.cnh.dao.OrderDao;
import com.cnh.dataobject.MiaoShaOrder;
import com.cnh.dataobject.OrderInfo;
import com.cnh.dataobject.User;
import com.cnh.enums.OrderEnum;
import com.cnh.redis.OrderKey;
import com.cnh.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class OrderService {

@Autowired
OrderDao  orderDao;


    @Autowired
    RedisService redisService;


    public MiaoShaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {

        //return  orderDao.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);

        //todo:本来是查询数据库的，但是现在只要查询缓存就可以了
        MiaoShaOrder miaoShaOrder=   redisService.get(OrderKey.getMiaoshaOrderByUidGoodsId,""+userId+"_"+goodsId,MiaoShaOrder.class);
return miaoShaOrder;
    }

    @Transactional
    public OrderInfo createOrder(User user, GoodsVo goodsVo) {
OrderInfo orderInfo = new OrderInfo();
orderInfo.setCreateDate(new Date());
//这个地址为什么是一个long类型
orderInfo.setAddressId(0L);
        orderInfo.setGoodsCount(1);
orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChanner(1);
        orderInfo.setStatus(OrderEnum.ORDER_NOPAY.getCode());
        orderInfo.setUserId(user.getId());
        log.info("userid的值,{}",user.getId());
        //插入这个user——info的时候会返回orderid
        orderDao.insert(orderInfo);

        /**
         * 生成的订单列放入redis集合中，永久存放，下次查询从redis中查询
         */


        //然后插入miaosha——order这个表
        MiaoShaOrder miaoShaOrder = new MiaoShaOrder();
        miaoShaOrder.setGoodsId(goodsVo.getId());
        miaoShaOrder.setOrderId(orderInfo.getId());
        miaoShaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoShaOrder);

        redisService.set(OrderKey.getMiaoshaOrderByUidGoodsId,""+user.getId()+"_"+goodsVo.getId(),miaoShaOrder);
return orderInfo;
    }


    //查找订单
    public OrderInfo getOrderId(long orderId) {

return orderDao.getOrderById(orderId);



    }
}
