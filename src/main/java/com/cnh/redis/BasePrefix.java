package com.cnh.redis;

/**
 * 抽象类，实现keyprefix接口，但是为了每个前缀都是不一样的
 * 比如用户模块用user开头，订单模块用order开头
 */
public abstract class BasePrefix implements KeyPrefix {

    //过期时间
private int expireSeconds;
    //前缀
    private String prefix;


    //没有过期时间的构造函数
    public BasePrefix(String prefix){
        //这个this（）表示调用了本类下面这个构造函数，
this(0,prefix);
    }


    //有过期时间的构造函数
    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//默认0 代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {

        // getClass().getSimpleName(); 得到某个类的类名 ，例如：User/Order
        String className = getClass().getSimpleName();

        return className+":"+prefix;
    }
}
