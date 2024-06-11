package com.otters.computerstore.component.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunnerCustomer(CustomerRepo customerRepo){
        return args -> {};
    }
}
