package com.mengck.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;
}
