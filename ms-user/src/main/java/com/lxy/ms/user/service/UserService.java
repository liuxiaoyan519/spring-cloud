package com.lxy.ms.user.service;

import com.lxy.ms.user.User;
import com.lxy.ms.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public void addUser(User user){
        userMapper.insert(user);
    }

    public List<User> selectList() {
        return userMapper.selectAll();
    }
}
