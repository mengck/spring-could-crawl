package com.mengck.crawl.task.controller;



import com.mengck.common.domain.Task;
import com.mengck.common.mapper.TaskMapper;
import com.mengck.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/task/insert")
    @PreAuthorize("hasAuthority('/task/insert')")
    public ResponseResult insertTask(@RequestBody Task task){

        taskMapper.insert(task);

        ResponseResult result = new ResponseResult<>();

        return result;
    }
}
