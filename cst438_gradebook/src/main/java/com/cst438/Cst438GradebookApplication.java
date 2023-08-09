package com.cst438;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.cst438.services.RegistrationServiceDefault;
import com.cst438.services.RegistrationServiceMQ;
import com.cst438.services.RegistrationServiceREST;

@SpringBootApplication
public class Cst438GradebookApplication  {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Cst438GradebookApplication.class, args);
	}

}
