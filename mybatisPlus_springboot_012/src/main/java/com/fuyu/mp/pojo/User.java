package com.fuyu.mp.pojo;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User extends Model<User> {
//    @TableId(type = IdType.AUTO)   //指定id类型为自增长
    private Long id;
    private String userName;
    //第一个参数查询时不返回字段的值  第二个参数  插入数据时进行自动填充
    @TableField(select = false,fill = FieldFill.INSERT)
    private String password;
    private String name;
    private Integer age;
    @TableField(value="email")  //指定数据库表中字段名
    private String mail;
    @TableField(exist =false)
    private String address; //该字段在数据库表中是不存在的

    @Version  //乐观锁版本字段
    private Integer version;

    @TableLogic // 逻辑删除字段 1  删除  0 未被删除
    private Integer deleted;
}