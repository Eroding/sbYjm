package com.cnh.controller;

import com.cnh.Vo.GoodsVo;
import com.cnh.Vo.OrderDetailVo;
import com.cnh.dataobject.Goods;
import com.cnh.dataobject.OrderInfo;
import com.cnh.dataobject.User;
import com.cnh.enums.OrderEnum;
import com.cnh.enums.ResultEnum;
import com.cnh.exception.SellException;
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.result.Result;
import com.cnh.service.GoodsService;
import com.cnh.service.OrderService;
import com.cnh.service.UserService;
import com.cnh.util.CookieUtil;
import com.cnh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, User user, HttpServletResponse response,
                                      HttpServletRequest request,
                                      @RequestParam("orderId")long orderId){
        log.info("[此时orderId]",orderId);
        String token =  CookieUtil.get(request, UserKey.COOKIE_NAME_TOKEN).getValue();
        user =userService.getByToken( token,response);
        model.addAttribute("user",user);
        if(user ==null){
            return ResultUtil.error(ResultEnum.USER_EMPTY);
        }
        OrderInfo orderInfo=orderService.getOrderId(orderId);

        if(orderInfo ==null){
            throw new SellException(OrderEnum.ORDER_NOT_EXIT);
        }
        long goodsId = orderInfo.getGoodsId();
        GoodsVo goodsVo=goodsService.getGoodsVoById(goodsId);
OrderDetailVo vo = new OrderDetailVo();
vo.setOrderInfo(orderInfo);
vo.setGoods(goodsVo);
log.info("[此时vo]",vo);
        return ResultUtil.success(vo);
    }



}
