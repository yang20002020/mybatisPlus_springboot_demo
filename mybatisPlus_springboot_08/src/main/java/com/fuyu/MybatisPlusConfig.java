package com.fuyu;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.fuyu.mp.plugins.MyInterceptor;
import com.fuyu.mp.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@MapperScan("com.fuyu.mp.mapper")
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 自定义拦截器（插件）
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    /**
     *  sql分析插件
     * @return
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());// 全表更新、删除的阻断器
        sqlExplainInterceptor.setSqlParserList(sqlParserList);
        return sqlExplainInterceptor;
    }


}
