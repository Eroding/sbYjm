package com.cnh.dao;

import com.cnh.Vo.GoodsVo;
import com.cnh.dataobject.Goods;
import com.cnh.dataobject.MiaoShaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.miaosha_stock,miaosha_price,start_date,end_time from miaosha_goods mg left join goods g on mg.goods_id =g.id")
    public List<GoodsVo> listGoodsVo();


    @Select("select g.*,mg.miaosha_stock,miaosha_price,start_date,end_time from miaosha_goods mg left join goods g on mg.goods_id =g.id where g.id =#{goodsId}")
    GoodsVo getGoodsVoById(@Param("goodsId") long goodsId);

    /**
     * 库存会出现超卖现象,所以要先判断库存大于0
     * @param g
     * @return
     */
    @Update("update miaosha_goods set miaosha_stock = miaosha_stock -1 where goods_id =#{goodsId} and miaosha_stock > 0")
    int reduceStock(MiaoShaGoods g);
}
