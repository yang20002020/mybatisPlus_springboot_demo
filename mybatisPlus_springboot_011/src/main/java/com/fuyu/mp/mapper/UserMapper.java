package com.fuyu.mp.mapper;
import com.fuyu.mp.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MyBaseMapper<User> {
    User findById(Long id);

}
