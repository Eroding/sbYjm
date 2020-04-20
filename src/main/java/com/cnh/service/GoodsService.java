package com.cnh.service;


import com.cnh.Vo.GoodsVo;
import com.cnh.dao.GoodsDao;
import com.cnh.dataobject.Goods;
import com.cnh.dataobject.MiaoShaGoods;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品的service，这个项目就没有service 的接口，直接就实现了
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoById(long goodsId) {
        return goodsDao.getGoodsVoById(goodsId);
    }

    //这个是减少库存的方法，应该判断是否真正成功了
    public boolean reduceStock(GoodsVo goodsVo) {
        MiaoShaGoods g = new MiaoShaGoods();
        g.setGoodsId(goodsVo.getId());
       int i = goodsDao.reduceStock(g);
       return i>0;
    }
}
