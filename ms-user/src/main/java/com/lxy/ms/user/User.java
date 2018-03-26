package com.lxy.ms.user;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="user")
@Data
public class User implements Serializable{
    @Id
    private Long userId;
    private String userName;
    private String password;
    private String phone;
}
