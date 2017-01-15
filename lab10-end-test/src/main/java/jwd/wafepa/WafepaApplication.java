package jwd.wafepa;

import org.springframework.boot.SpringApplication;
import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WafepaApplication 
extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WafepaApplication.class, args);
	}
	
	

}
