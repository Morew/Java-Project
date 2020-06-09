package com.nustti.entity;

import lombok.Data;
import org.apache.catalina.User;

@Data
public class UserEntity extends  BaseEntity{

    private String username;

    private String password;

    private String phone;

    private String email;


}
