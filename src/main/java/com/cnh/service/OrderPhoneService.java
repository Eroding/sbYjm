package com.cnh.service;

import com.cnh.dao.OrderMapper;
import com.cnh.dao.PhoneMapper;
import com.cnh.dataobject.OrderUserPhone;
import com.cnh.dataobject.Orders;
import com.cnh.dataobject.Orders;
import com.cnh.dataobject.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPhoneService {
    @Autowired
    OrderMapper orderMapper;

    public    void  add(Orders order){
        orderMapper.insert(order);
    }

    public    void  delete(int id){
        orderMapper.delete(id);
    }

    public    void  update(Orders order){
        orderMapper.update(order);
    }

    public    OrderUserPhone  findById(int id){
       return orderMapper.findById(id);
    }


    public    Orders  findById2(int id){
        return orderMapper.findById2(id);
    }


    public List<OrderUserPhone> findByUserIdAndStatus(Integer userId,Integer status){
        return   orderMapper.findByUserIdAndStatus(userId,status);
    }

    public List<OrderUserPhone> findAll(){
        return   orderMapper.findAll();
    }

    public List<OrderUserPhone> findByUserIdPhone(int userId){
        return   orderMapper.findByUserIdPhone(userId);
    }


    public List<OrderUserPhone> findAll(int status){
        return   orderMapper.findAllByStatus(status);
    }
}
