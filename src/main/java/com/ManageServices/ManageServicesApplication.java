package com.ManageServices;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin//允许跨越访问
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.ManageServices.dao")
@EnableTransactionManagement
public class ManageServicesApplication{

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(ManageServicesApplication.class, args);
	}

}