package com.levin.demo.common.returnJson;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 该配置类覆盖了原有的HttpMessageConverters处理方式，采用fastjson处理方式
 */
@Configuration
public class WebConfigure extends WebMvcConfigurerAdapter  {

    @Bean
    public HttpMessageConverters useConverters() {

        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
    }
}
