package com.back.black;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn
@SpringBootApplication
@ComponentScan("com.back.black.entity")
@ComponentScan("com.back.black.Service")

public class BlackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackApplication.class, args);
	}
}