package com.mengck.crawl.user.controller;

import com.mengck.api.task.TaskApiOpenFeignClient;
import com.mengck.api.task.domain.TaskVO;
import com.mengck.common.domain.User;
import com.mengck.common.mapper.UserMapper;
import com.mengck.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TaskApiOpenFeignClient taskApiOpenFeignClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/test")
    public ResponseResult test(@RequestBody User user){

        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        userMapper.insert(user);


        ResponseResult result = new ResponseResult();
        return result;
    }
    @GetMapping("/test111")
    public String men(){
        TaskVO taskVO = new TaskVO();
        taskVO.setTaskCode("123");
        taskVO.setTaskName("dddd");
        taskApiOpenFeignClient.insertTask(taskVO);

        return "1111";
    }
}
