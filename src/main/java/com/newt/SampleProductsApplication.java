package com.newt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan("com.newt")
@EnableAutoConfiguration
@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
@EnableSwagger2
public class SampleProductsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SampleProductsApplication.class, args);
	}
}
