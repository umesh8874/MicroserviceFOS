package com.AN1D.an1d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.AN1D.an1d",exclude = {DataSourceAutoConfiguration.class,MongoAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {} )
public class An1dApplication {

	public static void main(String[] args) {
		SpringApplication.run(An1dApplication.class, args);
	}

}