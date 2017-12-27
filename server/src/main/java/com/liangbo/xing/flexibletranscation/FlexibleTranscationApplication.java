package com.liangbo.xing.flexibletranscation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = { "com.liangbo.xing.flexibletranscation.mapper" })
public class FlexibleTranscationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexibleTranscationApplication.class, args);
	}
}
