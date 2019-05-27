package com.ManageServices;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin//允许跨越访问
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.ManageServices.dao")
public class ManageServicesApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ManageServicesApplication.class);
	}
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(ManageServicesApplication.class, args);
	}
}