package com.AN1D.an1d;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages = {"com.AN1D.an1d.*"})
//@SpringBootApplication(scanBasePackages = "com.AN1D.an1d",exclude = {DataSourceAutoConfiguration.class})
//@EnableJpaRepositories("com.AN1D.an1d.Repository")
//@PropertySource(value = { "classpath:application.properties" })
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
public class An1dApplication {

	public static void main(String[] args) {
		SpringApplication.run(An1dApplication.class, args);
	}

}