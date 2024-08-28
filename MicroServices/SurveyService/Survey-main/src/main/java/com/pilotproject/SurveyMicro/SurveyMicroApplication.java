package com.pilotproject.SurveyMicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SurveyMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyMicroApplication.class, args);
	}

}
