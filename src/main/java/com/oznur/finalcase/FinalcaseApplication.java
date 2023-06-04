package com.oznur.finalcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinalcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalcaseApplication.class, args);
	}

}
