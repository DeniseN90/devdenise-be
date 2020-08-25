package com.devdenise.devdenisebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DevdeniseBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevdeniseBeApplication.class, args);
	}

}
