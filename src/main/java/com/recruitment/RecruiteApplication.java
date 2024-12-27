package com.recruitment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Recruitment API",
				version = "1.0",
				description = "API documentation for the recruitment platform"
		)
)public class RecruiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruiteApplication.class, args);
	}

}
