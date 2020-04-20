package com.cnh.redis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//以redis开头的文件都会被读取到
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {

    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;


}
