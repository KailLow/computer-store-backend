package com.otters.computerstore.component.saleProduct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleProductConfig   {
    @Bean
    CommandLineRunner commandLineRunnerSaleProduct(SaleProductRepo saleProductRepo){
        return args -> {};
    }
}