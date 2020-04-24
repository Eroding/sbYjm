package com.cnh.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnh.dataobject.Goods;
import com.cnh.dataobject.User;
/*import com.cnh.elasticSearchDao.GoodsRespository;
import com.cnh.elasticSearchDao.UserRespository;*/
import com.cnh.dto.IdCardDto;
import com.cnh.enums.ResultEnum;
/*import com.cnh.rabbitmq.MQSender;*/
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.result.Result;
import com.cnh.service.UserService;
import com.cnh.util.Base64Util;
import com.cnh.util.FileUtil;
import com.cnh.util.HttpUtil;
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
import java.net.URLEncoder;
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
    /**
     * 百度云获取身份证信息的id和密钥
     */
    // 官网获取的 API Key 更新为你注册的
    String clientId = "NMTQ1m037DIdptZPeqbbaCEG";
    // 官网获取的 Secret Key 更新为你注册的
    String clientSecret = "amK63jRsnEewMMMzp8bmjP9kP5fanUaw";

    //@ApiOperation(value = "身份证（头像这一面）照片校验")
    @RequestMapping("/idCarCheck")
    //@RequiresPermissions("hjjk:healthTeam:page")
    public Object idCarCheck(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        IdCardDto idCardDto = new IdCardDto();  //返回前端的dto
        try {
            // 本地文件路径,将传进来的地址改为本地路径image重新拼接

            //举例：	https://ht.zjhjjk.com//upload/null/202003/308f59044ee24928a9769964b1e00d48.png  /前22个值
          //  String filePathIndex = "/usr/local/yui-ui/hjjk";
            // String filePath = "C:\\Users\\Administrator\\Desktop\\1.jpg";
           // String filePath = filePathIndex + image.substring(22);
          //  System.out.println("filePath:" + filePath);

            byte[] imgData = FileUtil.readFileByBytes(image);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。

            String accessToken = userService.getAuth(clientId, clientSecret);

            String result = HttpUtil.post(url, accessToken, param);


            if (null != result && !result.equals("")) { //解析的身份证内容不为空
                JSONObject jsonObject = JSON.parseObject(result);

                JSONObject JSONObjectW = jsonObject.getJSONObject("words_result");

                JSONObject JSONObjectW1 = JSONObjectW.getJSONObject("住址");
                String address = JSONObjectW1.getString("words");
                idCardDto.setAddress(address);

                JSONObject JSONObjectW2 = JSONObjectW.getJSONObject("出生");
                String birth = JSONObjectW2.getString("words");
                idCardDto.setBirth(birth);

                JSONObject JSONObjectW3 = JSONObjectW.getJSONObject("公民身份号码");
                String id = JSONObjectW3.getString("words");
                idCardDto.setId(id);

                JSONObject JSONObjectW4 = JSONObjectW.getJSONObject("性别");
                String sex = JSONObjectW4.getString("words");
                idCardDto.setSex(sex);

                JSONObject JSONObjectW5 = JSONObjectW.getJSONObject("民族");
                String nation = JSONObjectW5.getString("words");
                idCardDto.setNation(nation);

                JSONObject JSONObjectW6 = JSONObjectW.getJSONObject("姓名");
                String name = JSONObjectW6.getString("words");
                idCardDto.setName(name);

            }

            System.out.println(result);
            return  ResultUtil.success(idCardDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCardDto;
    }

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
