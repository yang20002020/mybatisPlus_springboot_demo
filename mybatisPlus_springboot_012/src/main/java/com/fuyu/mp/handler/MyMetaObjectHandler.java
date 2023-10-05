package com.fuyu.mp.handler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //先获取password值，再进行判断，如果为空，就进行填充，如果不为空，就不做处理
        Object password = getFieldValByName("password", metaObject);
        if (null == password) {
            //字段为空，可以进行填充
            setFieldValByName("password", "888888", metaObject);
        }
    }

    /**
     * 更新数据时自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
