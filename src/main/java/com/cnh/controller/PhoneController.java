package com.cnh.controller;

import com.cnh.dao.PhoneMapper;
import com.cnh.dataobject.Phone;
import com.cnh.dto.PhoneWithCommentDto;
import com.cnh.service.PhoneService;

import com.cnh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/phone")
@Slf4j
public class PhoneController {
    @Autowired
    PhoneService phoneService;

    @RequestMapping("/add")
    public Object add(@RequestBody Phone phone) {
        phoneService.add(phone);
        log.info("qqqqqq");
        return ResultUtil.success();
    }


    @RequestMapping("/delete")
    public Object delete(int id) {
        phoneService.delete(id);
        return ResultUtil.success();
    }


    @RequestMapping("/findAll")
    public Object findAll(String status, String phoneName) {
       List<PhoneWithCommentDto> phone =  phoneService.findAll(status,phoneName);
        return ResultUtil.success(phone);
    }


    @RequestMapping("/findById")
    public Object findById(int id) {
       Phone phone =  phoneService.findById(id);
        return ResultUtil.success(phone);
    }

    @RequestMapping("/update")
    public Object update(@RequestBody Phone phone) {
        phoneService.update(phone);
        return ResultUtil.success();
    }


}
