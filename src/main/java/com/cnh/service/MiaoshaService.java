package com.cnh.service;


import com.cnh.Vo.GoodsVo;
import com.cnh.dataobject.MiaoShaOrder;
import com.cnh.dataobject.OrderInfo;
import com.cnh.dataobject.User;
import com.cnh.redis.MiaoshaKey;
import com.cnh.redis.RedisService;
import com.cnh.util.MD5Util;
import com.cnh.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.beans.Transient;

//todo:因为秒杀是一个事务，必须放在一起，所以现在做成一个service
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;


    @Autowired
    RedisService redisService;


    @Transactional
    public OrderInfo miaosha(User user, GoodsVo goodsVo) {

        //减库存 下订单 写入秒杀订单
       boolean success =  goodsService.reduceStock(goodsVo);
       if(success){
            return orderService.createOrder(user,goodsVo);
       }
       //商品秒杀完了
   else{
       setGoodsOver(goodsVo.getId());
           return null;
       }

    }



    /**
     * 前端轮训过来的方法
     * @param userId
     * @param goodsId
     */
    public long getMiaoshaResult(long userId, long goodsId) {
       MiaoShaOrder order =  orderService.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);

if(order != null){
    return order.getOrderId();
}else{
boolean isOver = getGoodsOver(goodsId);
if(isOver) {
    //表示商品卖完了
    return -1;

}else{
    //继续轮训
        return 0;
    }
}
        }






    private boolean getGoodsOver(long goodsId) {

        return  redisService.exit(MiaoshaKey.isOver,""+goodsId);
    }





    private void setGoodsOver(long goodsId) {

        redisService.set(MiaoshaKey.isOver,""+goodsId,true);
    }



   public String createMiaoshaPath(User user,long goodsId){

        //这里设置一个随机数，会返回给前端，但是也要放在redis中
        String  str = MD5Util.md5(UUIDUtil.uuid())+"cnh";
        redisService.set(MiaoshaKey.getMiaoShaPath,""+user.getId()+"_"+goodsId,str);
        return str;
    }






    public boolean checkPath(User user, long goodsId, String path) {


if(user==null||StringUtils.isEmpty(path)){
    return false;
}
        String pathOld =  redisService.get(MiaoshaKey.getMiaoShaPath,""+user.getId()+"_"+goodsId,String.class);
return path.equals(pathOld);
    }


    /**
     * 校验前端传过来的验证码和后端是否一致
     * @param user
     * @param goodsId
     * @param verifyCode
     * @return
     */

    public boolean checkVerifyCode(User user, long goodsId, String verifyCode) {

        if(user ==null || goodsId <=0){
            return false;
        }

       String redisVerifyCode = redisService.get(MiaoshaKey.getMiaoShaVerifyCode,user.getId()+","+goodsId,String.class);

       if(redisVerifyCode.equals(verifyCode) ){
           //当验证成功的时候，把redis中的值删掉，避免冗余，还有减少压力
           redisService.delete(MiaoshaKey.getMiaoShaVerifyCode,user.getId()+","+goodsId);
return true;
       }else {

           return false;
       }


    }
}

