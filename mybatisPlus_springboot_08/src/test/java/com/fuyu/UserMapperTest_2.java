package com.fuyu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyu.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest_2 {


    @Test
    public void testSelectById() {
        User user = new User();
        user.setId(2L);
        User user2 = user.selectById();
        System.out.println(user2);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("刘备");
        user.setAge(30);
        user.setPassword("123456");
        user.setUserName("liubei");
        user.setMail("liubei@itcast.cn");
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(13L);//查询条件
        user.setAge(35); // 更新的数据
        boolean update = user.updateById();
        System.out.println(update);
    }
    @Test
    public void testDeleteById() {
        User user = new User();
        user.setId(13L);
        boolean delete = user.deleteById();
        System.out.println(delete);
    }

    @Test
    public void testSelectList() {
        User user = new User();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.le("age","20");
        List<User> users = user.selectList(userQueryWrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}




