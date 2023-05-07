package com.mengck.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengck.common.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
