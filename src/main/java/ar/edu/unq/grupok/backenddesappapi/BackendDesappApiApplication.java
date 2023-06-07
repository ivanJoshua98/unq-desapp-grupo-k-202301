package ar.edu.unq.grupok.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Configuration
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "CriptoP2P", 
								version = "1.0", 
								description = "P2P service to buy cryptocurrencies between people, with the aim of generating a trusted community to be able to exchange cryptocurrencies for argentine money"))
public class BackendDesappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappApiApplication.class, args);
	}

}
