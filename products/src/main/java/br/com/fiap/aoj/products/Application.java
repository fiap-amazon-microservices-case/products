package br.com.fiap.aoj.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static br.com.fiap.aoj.products.Application.BASE_PACKAGE;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { BASE_PACKAGE })
public class Application {

	public static final String BASE_PACKAGE = "br.com.fiap.aoj.products";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}