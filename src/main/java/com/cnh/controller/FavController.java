package com.cnh.controller;


import com.cnh.dataobject.Favorite;
import com.cnh.dataobject.Phone;
import com.cnh.enums.MiaoShaStatusEnum;
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
        //先判断以前是否收藏过
        Favorite favorite1 =     favService.findVByuserIdAndPhoneId(favorite.getUserId(),favorite.getPhoneId());
if(null!=favorite1){
    if(favorite1.getStatus()==1){//表示已经收藏过，不能再次收藏
return ResultUtil.error(MiaoShaStatusEnum.COLLECTION);
    }if(favorite1.getStatus()==0){//表示有记录，但是没有收藏
        favorite1.setStatus(1);
        favService.updateByPrimaryKey(favorite1);
        return ResultUtil.success();
    }
}
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
    public  Result<Object>  findVByuserIdAndPhoneId(int userId,int phoneId) {
        Favorite favorite =   favService.findVByuserIdAndPhoneId(userId,phoneId);
        return ResultUtil.success(favorite);
    }


}
