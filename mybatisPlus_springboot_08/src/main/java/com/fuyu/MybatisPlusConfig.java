package com.fuyu;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.fuyu.mp.plugins.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
