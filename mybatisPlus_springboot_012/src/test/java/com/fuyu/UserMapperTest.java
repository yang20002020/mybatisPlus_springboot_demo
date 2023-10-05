package com.fuyu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyu.mp.mapper.UserMapper;
import com.fuyu.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(20);
        user.setMail("test@itcast.cn");
        user.setName("曹操");
        user.setUserName("caocao");
        user.setPassword("123456");
        int result = this.userMapper.insert(user); //返回的result是受影响的行数，并不是自增后的id
        System.out.println("result = " + result);
        System.out.println(user.getId()); //自增后的id会回填到对象中
    }

    /**
     * 自动填充
     */
    @Test
    public void testInsert_1() {
        User user = new User();
        user.setName("关羽");
        user.setUserName("guanyu");
        user.setAge(30);
        user.setMail("guanyu@itast.cn");
        user.setVersion(1);
        int result = this.userMapper.insert(user);
        System.out.println("result = " + result);
    }

    @Test
    public void testSelectById(){
        //根据id查询数据
        User user = this.userMapper.selectById(1l);
        System.out.println(user);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(6L); //主键
        user.setAge(21); //更新的字段
        //根据id更新，更新不为null的字段
        this.userMapper.updateById(user);
    }
    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(22); //更新的字段
        //更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 6);//匹配id=6的用户数据
         //执行更新操作
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }
    @Test
    public void testUpdate_1() {
        //更新的条件以及需要更新的数据库字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 6).set("age", 23);
        //执行更新操作
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteById() {
        //根据id执行删除操作
        int result = this.userMapper.deleteById(6L);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age",24);
        columnMap.put("name","孙七");
        //将columnMap中的元素设置为删除的条件，多个之间为and关系
        int result = this.userMapper.deleteByMap(columnMap);
        System.out.println("result = " + result);
    }

    @Test
    public void testDelete(){
        //用法一
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //删除user_name=caocao1 且password=123456的数据
        wrapper.eq("user_name","caocao1").eq("password","123456");

        int result = this.userMapper.delete(wrapper);
        System.out.println("result = " + result);

    }

    @Test
    public void testDelete_1(){

        //用法二 不需要写数据库字段
        User user=new User();
        user.setPassword("123456");
        user.setUserName("caocao");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        //根据包装条件做删除
        int result = this.userMapper.delete(wrapper);
        System.out.println("result = " + result);

    }

    @Test
    public void testDeleteBatchIds() {
        //根据id集合批量删除
        int result = this.userMapper.deleteBatchIds(Arrays.asList(10L,20L));
        System.out.println("result = " + result);
    }

    @Test
    public void testSelectBatchIds() {
        //根据id集合批量查询
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("name", "李四");
        //根据条件查询一条数据，如果结果超过一条会报错
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);

    }


    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 21); //年龄大于21岁
           //根据条件查询数据条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 21); //年龄大于21岁
        //根据条件查询数据
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.like("email", "itcast");
        //第一个参数  当前页  第二个参数 每一页几条数据
        Page<User> page = new Page<>(1,5);//查询第一页，每一页5条数据
        //根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("数据总页数：" + iPage.getPages());
        System.out.println("当前页数:"+iPage.getCurrent());
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     * 自定义的方法
     */
    @Test
    public void testfindById() {
        User user = this.userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testAllEq(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
   //    SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (password IS NULL AND name = ? AND age = ?)
        wrapper.allEq(params);
        List<User> users = this.userMapper.selectList(wrapper);
        for(User user :users){
            System.out.println(user);
        }
    }

    @Test
    public void testAllEq_1(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //  SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (name = ? AND age = ?)
        wrapper.allEq(params,false);
        List<User> users = this.userMapper.selectList(wrapper);
        for(User user :users){
            System.out.println(user);
        }
    }

    @Test
    public void testAllEq_2(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        //SELECT * FROM tb_user WHERE name = ? AND age = ?
        wrapper.allEq((k, v) -> (k.equals("name") || k.equals("age")),params);
        List<User> users = this.userMapper.selectList(wrapper);
        for(User user :users){
            System.out.println(user);
        }
    }

    @Test
    public void testAllEq_3(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        //SELECT * FROM tb_user WHERE name = ? AND age = ?
        wrapper.allEq((k, v) -> (k.equals("name") || k.equals("id")),params);
        List<User> users = this.userMapper.selectList(wrapper);
        for(User user :users){
            System.out.println(user);
        }
    }


    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
      //SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE ?
        // Parameters: %曹%(String)
        wrapper.like("name", "曹");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testWrapper_1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testWrapper_or() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name","李四").or().eq("age", 24);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testWrapper_and() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE name = ? and age = ?
        wrapper.eq("name","李四").eq("age", 24);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testWrapper_select() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "李四")
                .or()
                .eq("age", 24)
                .select("id", "name", "age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     *  逻辑删除
     */
    @Test
    public void testDeleteById_logic(){
        this.userMapper.deleteById(2L);
    }

    /**
     *  验证 逻辑删除
     */
    @Test
    public void testSelectById_logic_1(){
        this.userMapper.selectById(2L);
    }

}




