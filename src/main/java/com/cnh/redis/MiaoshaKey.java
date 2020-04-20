package com.cnh.redis;

public class MiaoshaKey extends BasePrefix{
    //永久不失效
    private MiaoshaKey(String prefix){
        super(prefix);
    }

    private MiaoshaKey(int expireSeconds,String prefix){
        super(60,prefix);
    }

    public static MiaoshaKey isOver = new MiaoshaKey("goodsOver");

    //60秒用来存放这个随机数
    public static MiaoshaKey getMiaoShaPath = new MiaoshaKey(60,"miaoshaPath");

    //点击秒杀按钮时生成的验证码。
    public static MiaoshaKey getMiaoShaVerifyCode = new MiaoshaKey(300,"miaoshaVerifyCode");

    //访问次数，防刷
  //  public static MiaoshaKey access = new MiaoshaKey(5,"access");
/**
 * 传进一个限制次数
 *
 */

public static MiaoshaKey withTimes(int expireSeconds){
    return new MiaoshaKey(expireSeconds,"access");
}

}
