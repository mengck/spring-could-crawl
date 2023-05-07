package com.mengck.crawl.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengck.common.config.LoginUser;
import com.mengck.common.domain.User;
import com.mengck.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserDetailServiceIml implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        // 获取用户信息
        // 获取角色权限
        // 封装角色权限 到loginUser 当中

        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getUserName, username);

        User user = userMapper.selectOne(eq);

        if(user == null){
            throw new RuntimeException("用户数据不存在");
        }

        List<String> roles = new ArrayList<>(Arrays.asList("123"));

        return new LoginUser(user,roles);
    }
}
