package com.zyc.mawen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyc.mawen.mapper")
public class MawenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MawenApplication.class, args);
	}

}
