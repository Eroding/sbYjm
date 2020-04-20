package com.cnh.controller;

import com.cnh.Vo.GoodsDetailVo;
import com.cnh.Vo.GoodsVo;
import com.cnh.dataobject.User;
import com.cnh.enums.MiaoShaStatusEnum;
import com.cnh.redis.ListKey;
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.result.Result;
import com.cnh.service.GoodsService;
import com.cnh.service.UserService;

import com.cnh.util.CookieUtil;
import com.cnh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;


    @Autowired
    GoodsService goodsService;


    //Thymaleaf的框架包
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;


    //在 构造WebContext时所用到的
    @Autowired
    ApplicationContext applicationContext;





    // @CookieValue()可以得到前端的url传来的cookie值,有可能会从request中去取，所以有优先级
    //todo：本来这个页面是返回到某个html页面，但是这次返回的是html源代码
    @RequestMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public String toLogin(Model model,HttpServletResponse response,HttpServletRequest request,User user){
/*                          @CookieValue(value =userService.COOKIE_NAME_TOKEN,required=false) String cookieToken,
                          @RequestParam(value =userService.COOKIE_NAME_TOKEN,required=false) String paramToken,*/

 /* if(StringUtils.isEmpty(cookieToken)&& StringUtils.isEmpty(paramToken)){
      return "login";
  }*/
//三目运算
        //paramToken优先考虑，如果为空，就选择cook 。不为空就选择
     //   String token =StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//todo:先注释掉，用来压测
  /*   String token =  CookieUtil.get(request,UserKey.COOKIE_NAME_TOKEN).getValue();
user =userService.getByToken( token,response);*/
       // model.addAttribute("user",user);
       // log.info("[在goodController的to——list中],user的id，{}",user.getId());
        //查询商品列表
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        model.addAttribute("goodsVoList",goodsVoList);

//把这个页面做缓存处理，放到redis中，先去redis中找，没有的话就添加到redis中
        String html = redisService.get(ListKey.listKey,"",String.class);
//如果不是空
if(!StringUtils.isEmpty(html)){
    log.info("[当html不为空的时候，从redis中取到]");
return html;
}
//如果是空，就缓存到redis中，手动渲染
        WebContext context =new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
       html= thymeleafViewResolver.getTemplateEngine().process("goods_list",context);
if(!StringUtils.isEmpty(html)){
    //放置的时间为一分钟，一分钟之后从redis中自动消失
    redisService.set(ListKey.listKey,"",html);
    log.info("[放置redis缓存中]");
}

        return html;
    }

//现在开始某个商品的详情
@RequestMapping(value = "/to_detail/{goodsid}",produces = "text/html")
@ResponseBody
    public String detail(Model model, User user,HttpServletResponse response,HttpServletRequest request,
                         @PathVariable("goodsid")long goodsid){

        //snowflake 算法生成商品id，不在使用uuid和自增，因为uuid的长度太长
    String token =  CookieUtil.get(request,UserKey.COOKIE_NAME_TOKEN).getValue();
    user =userService.getByToken( token,response);
model.addAttribute("user",user);

    log.info("[在goodController],user的id，{}",user.getId());

  GoodsVo goodVo =  goodsService.getGoodsVoById(goodsid);
model.addAttribute("goods",goodVo);

//需要知道秒杀的开始时间和结束时间,.getTime()就是取得毫秒的意思
  long startAt=  goodVo.getStartDate().getTime();

   long endAt = goodVo.getEndTime().getTime();
//获取当前时间
long now = System.currentTimeMillis();

//定义一个秒杀的状态
    int miaoshaStatus =0;

    //时间差，还没开始的话就是开始时间-当前时间
    int remainSecond =0;

if(now<startAt){//秒杀还没开始，倒计时
    miaoshaStatus= MiaoShaStatusEnum.UN_START.getCode();
    remainSecond =(int)(startAt-now)/1000;
}else if (now>endAt){//秒杀结束
miaoshaStatus=MiaoShaStatusEnum.FINISH.getCode();
remainSecond =-1;
    }else {//可以秒杀
    miaoshaStatus=MiaoShaStatusEnum.STARTING.getCode();
    remainSecond=0;
}
log.info("现在的进行的状态[],{}",miaoshaStatus);
model.addAttribute("miaoshaStatus",miaoshaStatus);
    model.addAttribute("remainSecond",remainSecond);

//把这个页面做缓存处理，放到redis中，先去redis中找，没有的话就添加到redis中
    String html = redisService.get(ListKey.listKey,""+goodsid,String.class);
//如果不是空
    if(!StringUtils.isEmpty(html)){
        log.info("[当html不为空的时候，从redis中取到]");
        return html;
    }
//如果是空，就缓存到redis中，手动渲染
    WebContext context =new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
    html= thymeleafViewResolver.getTemplateEngine().process("goods_detail",context);
    if(!StringUtils.isEmpty(html)){
        //放置的时间为一分钟，一分钟之后从redis中自动消失
        redisService.set(ListKey.listKey,""+goodsid,html);
        log.info("[放置redis缓存中]");
    }

    return html;

}



    //现在开始某个商品的详情,这个是页面静态化的接口，把页面缓存在前端，数据从后端传输过去
    @RequestMapping(value = "/to_detail2/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail2(Model model, User user, HttpServletResponse response, HttpServletRequest request,
                                         @PathVariable("goodsId")long goodsId){

        //snowflake 算法生成商品id，不在使用uuid和自增，因为uuid的长度太长
        String token =  CookieUtil.get(request,UserKey.COOKIE_NAME_TOKEN).getValue();
        user =userService.getByToken( token,response);
        model.addAttribute("user",user);

        log.info("[在goodController2],user的id，{}",user.getId());

        GoodsVo goodVo =  goodsService.getGoodsVoById(goodsId);
        model.addAttribute("goods",goodVo);
        log.info("[goodVo]"+goodVo);

//需要知道秒杀的开始时间和结束时间,.getTime()就是取得毫秒的意思
        long startAt=  goodVo.getStartDate().getTime();

        long endAt = goodVo.getEndTime().getTime();
//获取当前时间
        long now = System.currentTimeMillis();

//定义一个秒杀的状态
        int miaoshaStatus =0;

        //时间差，还没开始的话就是开始时间-当前时间
        int remainSecond =0;

        if(now<startAt){//秒杀还没开始，倒计时
            miaoshaStatus= MiaoShaStatusEnum.UN_START.getCode();
            remainSecond =(int)(startAt-now)/1000;
        }else if (now>endAt){//秒杀结束
            miaoshaStatus=MiaoShaStatusEnum.FINISH.getCode();
            remainSecond =-1;
        }else {//可以秒杀
            miaoshaStatus=MiaoShaStatusEnum.STARTING.getCode();
            remainSecond=0;
        }
        log.info("现在的进行的状态[],{}",miaoshaStatus);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSecond",remainSecond);

GoodsDetailVo vo = new GoodsDetailVo();
vo.setGoods(goodVo);
vo.setUser(user);
vo.setRemainSeconds(remainSecond);
vo.setMiaoshaStatus(miaoshaStatus);
log.info("[vo],{}"+vo);
        return ResultUtil.success(vo);



    }
}
