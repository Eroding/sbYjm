package com.cnh.dataobject;

public class UserContext {

    //当前线程
   private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

   public  static void setUser(User user){
       userThreadLocal.set(user);
   }

   public static User getUser(){
       return userThreadLocal.get();
   }
}
