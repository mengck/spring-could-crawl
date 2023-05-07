package com.mengck.api.task;


import com.mengck.api.task.domain.TaskVO;
import com.mengck.api.task.resp.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "crawl-task")
public interface TaskApiOpenFeignClient {

    @PostMapping("/task/insert")
    ResponseResult insertTask(@RequestBody TaskVO task);
}
