package com.mengck.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengck.common.domain.Task;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskMapper extends BaseMapper<Task> {
}
