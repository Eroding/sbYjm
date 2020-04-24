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


    private Integer auth;

    private String name;

    private String address;

    private String sex;

    private String birthday;

    private String nation;


    public User(long id, String nickname, String password, String salt, String head, Date registerDate, Date lastLoginDate, Integer loginCount,Integer mobile,Integer auth,String name,
                String address,String sex,String birthday,String nation) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
        this.head = head;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
        this.mobile = mobile;
        this.auth = auth;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.sex = sex;
        this.birthday = birthday;
        this.nation = nation;


    }

    public User() {
    }
}
