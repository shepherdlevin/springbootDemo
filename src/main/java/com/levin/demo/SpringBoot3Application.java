package com.levin.demo;

import com.levin.demo.controller.TestController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@MapperScan("com.levin.demo.mapper")//将项目中对应的mapper类的路径加进来就可以了
@EnableScheduling
//@EnableAspectJAutoProxy//添加对aspect的支持
public class SpringBoot3Application {
	final static String queueName = "hello";
	private TestController testCotroller;

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3Application.class, args);
	}

	@RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
	String index(){
		return "Hello Spring Boot!";
	}

	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}
}
