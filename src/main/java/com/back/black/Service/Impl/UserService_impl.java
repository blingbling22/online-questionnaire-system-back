package com.back.black.Service.Impl;


import com.back.black.Service.UserService;
import com.back.black.Mapper.UserMapper;
import com.back.black.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService_impl implements UserService {
    @Resource
    UserMapper userMapper;

    //按用户名查找
    public User findByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }
    //按id查找
    public User findByUid(int uid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", uid);
        return userMapper.selectOne(wrapper);

    }

    @Override
    public void updatePassword(String Password,String username) {
        userMapper.update(null, Wrappers.<User>update().set("password",Password).eq("username",username));
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }


}
