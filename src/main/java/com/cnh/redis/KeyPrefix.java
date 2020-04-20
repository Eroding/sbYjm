package com.cnh.redis;


/**
 * 作为redis 的key的前缀
 * 通用缓存的key封装步骤
 * 接口----抽象类-----实现类
 *
 *
 */
public interface KeyPrefix {

    /**
     * 过期时间
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();


}
