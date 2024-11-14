package com.indra.carrito;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);

	}

	@Bean
	public OpenAPI custonOpenApi(){
		return new OpenAPI()
				.info(new Info()
						.title("Carrito de compras")
						.version("1.0.0")
						.contact(new Contact().name("Mauricio Rodriguez Bello")
								.email("maorobe14@gmail.com"))
						.description("Prueba tecnica para Indra")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0")
								.url("http://www.apache.org/licenses/LICENSE-2.0.html")));
	}

}
