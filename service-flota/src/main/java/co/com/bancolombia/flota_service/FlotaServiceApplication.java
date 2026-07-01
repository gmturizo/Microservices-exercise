package co.com.bancolombia.flota_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FlotaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlotaServiceApplication.class, args);
	}

}
