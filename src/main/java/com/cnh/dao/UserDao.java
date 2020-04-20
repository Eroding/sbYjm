package com.cnh.dao;


import com.cnh.dataobject.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 这个dao加了mapper
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id =#{id}")
     User getById(@Param("id") long id);



    @Select("select * from user")
  List<User> findAll();


    @Delete("delete from user where id=#{id}")
     void delete(int id);


    @Select("select * from user where nickname =#{nickname}")
    User getByName(@Param("nickname") String nickname);


    @Update("update user set password =#{password},nickname=#{nickname},mobile=#{mobile} where id =#{id}")
    void update(User toBeuser);


    @Insert("insert into user (nickname,password)values (#{nickname},#{password}) ")
    void add(@Param("nickname")String nickname,@Param("password")String password);

}
