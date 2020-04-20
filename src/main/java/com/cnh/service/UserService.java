package com.cnh.service;


import com.cnh.Vo.LoginVo;
import com.cnh.dao.UserDao;
import com.cnh.dataobject.User;
import com.cnh.enums.ResultEnum;
import com.cnh.exception.Exceptions;
import com.cnh.exception.SellException;
import com.cnh.redis.RedisService;
import com.cnh.redis.UserKey;
import com.cnh.util.CookieUtil;
import com.cnh.util.MD5Util;
import com.cnh.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

@Autowired
UserDao userDao;

@Autowired
    RedisService redisService;


//admin查看其它所用用户信息
   public List<User> lookByAdmin(){
      // List<User> users = new ArrayList<>();
       List<User> users1 =   userDao.findAll();
       Iterator<User> it = users1.iterator();
       while(it.hasNext()){
           User user = (User)it.next();
           if("admin".equals(user.getNickname())){
               it.remove();
           }
       }
       return users1;
   }

    //更新
public void update(User user){
userDao.update(user);
}
    //删除
public  void delete(int id){
       userDao.delete(id);
}
    //查找自己信息
   public User lookBySelef(int id){
      return userDao.getById(id);
   }








//本来这个方法直接从mysql中根据id来查找到这个用户，但是当我们查找到用户的时候
    //就可以放到redis中
public User getById(long id){
 //todo:先去redis中取，如果没有的话就去mysql中取
   User user= redisService.get(UserKey.getById,""+id,User.class);
    if(user!=null){
        return user;
    }
   user = userDao.getById(id);
    //todo:如果数据库中是有的
    if(user!=null){
        redisService.set(UserKey.getById,""+id,user);
    }

    return  user;
}


    /**
     * 可能登陆时发生错误，这个不同于以前，以前都是根据两个条件一起查询，但是这个是一个错抛一个异常
     * @param loginVo
     */
    public User login(HttpServletResponse response,LoginVo loginVo) {
 if(loginVo ==null){
     throw new SellException(ResultEnum.PARAM_ERROR);
 }
      String nickName=  loginVo.getNickname();
        String formPass=  loginVo.getPassword();
//判断手机号是否存在

        User user =      userDao.getByName(nickName);


if(user==null){
throw  new SellException(ResultEnum.USER_EMPTY);
}
//验证密码
//这个是从数据库查询出来正确的加密加盐的密码
       String dbPass = user.getPassword();
//String saltDb = user.getSalt();
//计算得出
   //    String calcPass = MD5Util.formPassToDb(formPass,saltDb);

//判断
        if(!dbPass.equals(formPass)){
            throw new SellException(ResultEnum.PASSWORD);
        }

        /**
         * 分布式session
         */
        //只有登录的时候才会生成随机数，当bytoken 的时候不需要，只要更新有效期就行
        String token = UUIDUtil.uuid();
//        addCookie(user,response,token);
        System.out.println("aaa");
        return user;
    }


    /**
     * 注册
     * @param loginVo
     */
    public boolean access(HttpServletResponse response,LoginVo loginVo) {
        if(loginVo ==null){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        String nickName=  loginVo.getNickname();
        String formPass=  loginVo.getPassword();
//判断手机号是否存在

        User user =      userDao.getByName(nickName);


        if(user!=null){
            throw  new SellException(ResultEnum.USER_HAS);
        }
//验证密码
//这个是从数据库查询出来正确的加密加盐的密码
    //    String dbPass = user.getPassword();
//String saltDb = user.getSalt();
//计算得出
        //    String calcPass = MD5Util.formPassToDb(formPass,saltDb);

//判断
     /*   if(!dbPass.equals(formPass)){
            throw new SellException(ResultEnum.PASSWORD);
        }*/

        /**
         * 分布式session
         */
        //只有登录的时候才会生成随机数，当bytoken 的时候不需要，只要更新有效期就行
        String token = UUIDUtil.uuid();
        System.out.println("aaa");
        userDao.add(nickName,formPass);
        return true;
    }


    /**
     * 像这种public方法养成习惯是先判断参数校验，因为这个是公共的，先校验，防止这个方法让别人恶意用了
     * @param token
     * @return
     */
    public User getByToken(String token,HttpServletResponse response) {

        if(StringUtils.isEmpty(token)){
            return null;
        }
       User user =     redisService.get(UserKey.token,token,User.class);
        //先不着急返回，先把有效期延长
        if(user !=null){
            addCookie(user,response,token);
        }
return user;
    }


    /**
     * 更新某个用户，按照手机号（id）和新的密码，但是一般为求效率，不会直接更新某个查出来的对象。都会把改变
     * 的值设置到一个new的对象,因为要修改token，所以要前端传过来
     * @param
     * @param
     * @param token
     */

    public boolean updatePassword(long id,String formPass,String token){

        //取user，但是这个user也是向上面一样先从redis中取，没有的话去mysql取。取完放redis中
        User user = getById(id);
        if(user ==null){
            throw new SellException(ResultEnum.USER_EMPTY);
        }
        //这里表示更新了数据库，但是也要更新
        User toBeuser = new User();
        toBeuser.setId(id);
        toBeuser.setPassword(MD5Util.formPassToDb(formPass,user.getSalt()));
        userDao.update(toBeuser);
//处理redis中的数据，
        //先删除原来的，在添加
        //先删除id
redisService.delete(UserKey.getById,""+id);
user.setPassword(toBeuser.getPassword());
  //更新token，不能的删除
        redisService.set(UserKey.token,token,user);
return true;
    }








    //todo：这个方法是设置redis 和 cookie
    //todo;最重要的一步
//因为在登录成功和获取token都需要设置token。因为根据token来查找到用户名会随着时间推移而推移
    private void addCookie(User user,HttpServletResponse response,String token){

        //把生成的token放到redis中
        redisService.set(UserKey.token,token,user);

      //使用cookieUtil设置cookie
        CookieUtil.set(response,UserKey.COOKIE_NAME_TOKEN,token,UserKey.token.expireSeconds());


        /**
         * todo:以下这种方法是直接在方法里面设置cookie，现在把设置cookie和取cookie
         * todo：做成一个工具类，方便使用，上一个sell项目的redis设置是用template。
         * 而此次用的是jedis操作，所以又写了一个redis操作方法
         *
         */
    /*    //设置cookie
        Cookie cookie = new Cookie(UserKey.COOKIE_NAME_TOKEN,token);
        //设置cookie的有效期
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);*/
    }
}
