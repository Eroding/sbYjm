package com.cnh.dao;

import com.cnh.dataobject.MiaoShaOrder;
import com.cnh.dataobject.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id =#{userId} and goods_id=#{goodsId}")
    MiaoShaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channer" +
            ",status,create_date)values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}" +
            ", #{orderChanner},#{status},#{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);


    @Insert("insert into miaosha_order(user_id,order_id,goods_id)values(#{userId}, #{orderId}, #{goodsId})")
    int insertMiaoshaOrder(MiaoShaOrder miaoShaOrder);

    @Select("select * from order_info where id=#{orderId}")
    OrderInfo getOrderById(@Param("orderId") long orderId);
}
