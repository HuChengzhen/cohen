package com.huchengzhen.cohen.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String userName;
    private String email;
    private String passwordHash;
    private Date createDate;
    private Date lastLoginDate;
}
