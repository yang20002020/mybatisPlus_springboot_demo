package com.fuyu.mp.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyu.mp.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findById(Long id);
}
