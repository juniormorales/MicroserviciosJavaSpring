package vass.assessment.server.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProductosApplication.class, args);
	}

}
