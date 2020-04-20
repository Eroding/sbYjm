package com.cnh.controller;


import com.cnh.dataobject.Favorite;
import com.cnh.dataobject.Phone;
import com.cnh.result.Result;
import com.cnh.service.FavService;
import com.cnh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fav")
@Slf4j
public class FavController {

@Autowired
FavService favService;

    //收藏
    @RequestMapping("/add")
    public Result<Object> add(Favorite favorite) {
        favorite.setStatus(1);
        favService.insert(favorite);
        return ResultUtil.success();
    }

    //取消收藏
    @RequestMapping("/updateStatus")
    public  Result<Object> updateStatus(int id) {

      Favorite favorite =   favService.selectByPrimaryKey(id);
        favorite.setStatus(0);
        favService.updateByPrimaryKey(favorite);
        return ResultUtil.success();
    }


    //查看自己收藏了哪些手机
    @RequestMapping("/findVById")
    public  Result<List<Favorite>> findVById(int userId) {
        List<Favorite> favorite =   favService.findAll(userId);
      //  favorite.setStatus(0);
     //   favService.updateByPrimaryKey(favorite);
        return ResultUtil.success(favorite);
    }

    //查看自己是否收藏了该手机
    @RequestMapping("/findVByuserIdAndPhoneId")
    public  Result<Object>  findVByuserIdAndPhoneId(@RequestBody int userId, @RequestBody int phoneId) {
        Favorite favorite =   favService.findVByuserIdAndPhoneId(userId,phoneId);
        return ResultUtil.success(favorite);
    }


}
