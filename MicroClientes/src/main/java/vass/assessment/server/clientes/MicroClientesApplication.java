package vass.assessment.server.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroClientesApplication.class, args);
	}

}
