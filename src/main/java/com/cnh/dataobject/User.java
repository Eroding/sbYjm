package com.cnh.dataobject;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "quicksell",type = "user")
public class User {
@Id
    private long id;

    private String nickname;

    private String password;

    private String salt;

    private String head;

    private Date registerDate;

    private Date lastLoginDate;

    private Integer loginCount;

    private Integer mobile;

    private Integer userId;


    public User(long id, String nickname, String password, String salt, String head, Date registerDate, Date lastLoginDate, Integer loginCount,Integer userId) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
        this.userId = userId;
        this.head = head;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
    }

    public User() {
    }
}
