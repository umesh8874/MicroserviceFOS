package com.AN1D.an1d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@ComponentScan(basePackages = {"com.AN1D.an1d.*"})
@SpringBootApplication(scanBasePackages = "com.AN1D.an1d",exclude = {DataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.AN1D.an1d.Repository")
@PropertySource(value = { "classpath:application.properties" })
public class An1dApplication {

	public static void main(String[] args) {
		SpringApplication.run(An1dApplication.class, args);
	}

}