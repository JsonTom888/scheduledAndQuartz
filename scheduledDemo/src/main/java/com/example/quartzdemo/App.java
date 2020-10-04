package com.example.quartzdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.quartzdemo.dao")
//@ComponentScan(basePackages = {"com.example.quartzdemo.dao"})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
