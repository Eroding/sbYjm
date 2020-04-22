package com.cnh.controller;


import com.cnh.dataobject.OrderUserPhone;
import com.cnh.dataobject.Orders;
import com.cnh.dataobject.Phone;

import com.cnh.enums.MiaoShaStatusEnum;
import com.cnh.result.Result;
import com.cnh.service.OrderPhoneService;

import com.cnh.service.PhoneService;
import com.cnh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/orderPhone")
public class OrderPhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    OrderPhoneService orderPhoneService;

    @RequestMapping("/add")
    @Transactional
    public Result add(@RequestBody Orders order) {
        System.out.println("phone:"+order.getPhoneId());
        Phone phone =phoneService.findById(order.getPhoneId()) ;

        if(Integer.valueOf(phone.getStore())<=0){ //库存不足
            return ResultUtil.error(MiaoShaStatusEnum.STOCK_EMPTY);
        }
        order.setStatus("0");  //未发货
        orderPhoneService.add(order);
       // Phone phone = phoneService.findById(order.getPhoneId());
        phone.setStore(String.valueOf(Integer.valueOf(phone.getStore())-1));
        System.out.println("store"+phone.getId());
        phoneService.update(phone);
        return ResultUtil.success();
    }


    @RequestMapping("/delete")
    public Object delete(int id) {
        orderPhoneService.delete(id);
        return ResultUtil.success();
    }


    @RequestMapping("/update")
    public Object update(@RequestBody Orders order) {
        orderPhoneService.update(order);
        return ResultUtil.success();
    }


    @RequestMapping("/findAll")
    public Object findAll(Integer status) {
        if(Objects.isNull(status)){
            return ResultUtil.success(orderPhoneService.findAll());
        }
        return ResultUtil.success(orderPhoneService.findAll(status));
    }


    @RequestMapping("/findById")
    public Object findById(int id) {
        OrderUserPhone o =  orderPhoneService.findById(id);
        return ResultUtil.success(o);
    }


    /**
     * 用户查看自己买了哪些手机
     * @param userId
     * @return
     */
    @RequestMapping("/findByUserIdAndStatus")
    public Object findByUserIdAndStatus(Integer userId,Integer status) {
      List<OrderUserPhone>   o =  orderPhoneService.findByUserIdAndStatus(userId,status);
        return ResultUtil.success(o);
    }



    /**
     * 用户查看自己发布的手机有哪些人下单
     * @param userId
     * @param
     * @return
     */
    @RequestMapping("/findByUserIdPhone")
    public Object findByUserIdPhone(int userId) {
        List<OrderUserPhone>   o =  orderPhoneService.findByUserIdPhone(userId);
        return ResultUtil.success(o);
    }



    /**
     * 发货
     * @param id
     * @return
     */
    @RequestMapping("/updateStatus")
    public Object updateStatus(int id,String status) {

        Orders o = orderPhoneService.findById2(id);
         o.setStatus(status);
        orderPhoneService.update(o);
        return ResultUtil.success();
    }

}
