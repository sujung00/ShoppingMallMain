package com.shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.shoppingmall")   // com.shoppingmall 패키지 아래에 있는 스프링빈들 탐색
@SpringBootApplication
public class ShoppingMallApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingMallApplication.class, args);
	}

}
