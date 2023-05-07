package com.mengck.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("task")
public class Task {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String taskName;

    private String taskCode;
}
