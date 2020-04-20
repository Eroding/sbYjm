package com.cnh.service;

import com.cnh.dao.OrderMapper;
import com.cnh.dao.PhoneMapper;
import com.cnh.dataobject.Orders;
import com.cnh.dataobject.Phone;
import com.cnh.dto.PhoneWithCommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PhoneService {
    @Autowired
    PhoneMapper phoneMapper;

    @Autowired
    OrderMapper orderMapper;

    public void add(Phone phone){
         phoneMapper.insert(phone);
    }

    public Phone findById(int id) {
        return phoneMapper.findById(id);
    }

    public void delete(int id) {
       phoneMapper.delete(id);
    }

    public int update(Phone phone) {
        return phoneMapper.update(phone);
    }

    /**
     * 手机列表页面带上评论,未测试
     * @param status
     * @param phoneName
     * @return
     */
    public List<PhoneWithCommentDto> findAll(String status, String phoneName){
        List<PhoneWithCommentDto> list = new ArrayList<>();

        List<Phone> phones =   phoneMapper.findAll(status,phoneName);

        for (Phone phone:phones) {
          List<Orders> orders =   orderMapper.findByPhoneId(phone.getId());
            PhoneWithCommentDto phoneWithCommentDto = new PhoneWithCommentDto();
            List<String> coms = new ArrayList<>();
            for (Orders orders1:orders
                 ) {
             String com =   orders1.getComment();
                coms.add(com);
            }

            BeanUtils.copyProperties(phone,phoneWithCommentDto);
            phoneWithCommentDto.setComments(coms);
            list.add(phoneWithCommentDto);
        }
       return list;
    }



}
