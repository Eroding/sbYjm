package com.cnh.controller;


import com.cnh.dataobject.Goods;
import com.cnh.dataobject.User;
/*import com.cnh.elasticSearchDao.GoodsRespository;
import com.cnh.elasticSearchDao.UserRespository;*/
import com.cnh.enums.ResultEnum;
/*import com.cnh.rabbitmq.MQSender;*/
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.result.Result;
import com.cnh.service.UserService;
import com.cnh.util.ResultUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/demo")
public class  DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;



 /*   @Autowired
    private UserRespository userRespository;*/

 /*   @Autowired
    private MQSender mqSender;*/



    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
//    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String filePath = "D:\\wamp\\www\\img\\";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        String fileName =  UUID.randomUUID().toString() + file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "http://localhost:8033/img/" + fileName;
        } catch (IOException e) {

                    log.error(e.toString(), e);
        }
        return "fail";
    }


@RequestMapping("/hello")
    public String hello(Model model) {
    model.addAttribute("name","cnh");
    return "hello";
}

/*    *//**
     * 测试elasearch
     * @param
     * @return
     *//*
    @GetMapping("/serachesave")
    public String save(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String date1 = df.format(new Date());
       Date date = new Date();
        Date date3 = new Date();
        String date2 = df.format(new Date());
            Long s = 18758342969L;

User  ss = new User(s,"ccc","sdsdsdsdsds","sdsdsds","sdsd",
        date,date3,1);

userRespository.save(ss);
return "success";
    }*/



    @RequestMapping("/info")
    @ResponseBody
    public Result<User> hello(User user) {

        return ResultUtil.success(user);
    }


/*    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq() {
        mqSender.send("mq");
        return ResultUtil.success("mq");
    }

    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> mqTopic() {
        mqSender.sendTopic("mq");
        return ResultUtil.success("mq");
    }


    @RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> mqFanout() {
        mqSender.sendFanout("mq");
        return ResultUtil.success("mq");
    }



    @RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> mqHeader() {
        mqSender.sendHeader("mq");
        return ResultUtil.success("mq");
    }*/

@RequestMapping("/find")
@ResponseBody
public Result<User> find(){
  User user = userService.getById(1);
    return ResultUtil.success(user);
}


    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> get(){

       User user =  redisService.get(UserKey.token,"name1",User.class);

        return ResultUtil.success(user);
    }


@RequestMapping("/redis/set")
@ResponseBody
public Result<Boolean> set(){

    User user = new User();
    user.setId(4);
    user.setNickname("ccc");

  boolean v1 =  redisService.set(UserKey.token,"name1",user);
    return ResultUtil.success(v1);
}
}
