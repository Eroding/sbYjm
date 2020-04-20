package com.cnh.service;

import com.cnh.dao.FavoriteMapper;
import com.cnh.dataobject.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavService {

    @Autowired
    FavoriteMapper favoriteMapper;

    public  int deleteByPrimaryKey(Integer id){
        return favoriteMapper.deleteByPrimaryKey(id);
    }

    public int insert(Favorite record){
        return favoriteMapper.insert(record);
    }


    public int insertSelective(Favorite record){
        return favoriteMapper.insertSelective(record);
    }


    public  Favorite selectByPrimaryKey(Integer id){
        return favoriteMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Favorite record){
        return favoriteMapper.updateByPrimaryKeySelective(record);
    }

    public    int updateByPrimaryKey(Favorite record){
        return favoriteMapper.updateByPrimaryKey(record);
    }

    //查看自己的收藏
    public  List<Favorite> findAll(int userId){
        return favoriteMapper.findAll(userId);
    }

    public  Favorite findVByuserIdAndPhoneId(int userId,int phoneId){
        return favoriteMapper.findVByuserIdAndPhoneId(userId,phoneId);
    }


}
