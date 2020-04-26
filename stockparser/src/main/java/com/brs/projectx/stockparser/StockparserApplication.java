package com.brs.projectx.stockparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class StockparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockparserApplication.class, args);
	}

}
