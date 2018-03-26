package com.foodstore.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
/**
 * Classe responsável por inicializar a aplicação
 *
 * @author dhsanchesp
 */
@SpringBootApplication
@EnableCaching
public class FoodstoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FoodstoreApplication.class, args);
    }
}
