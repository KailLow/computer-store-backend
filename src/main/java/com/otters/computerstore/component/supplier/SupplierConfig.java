package com.otters.computerstore.component.supplier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierConfig {
    @Bean
    CommandLineRunner commandLineRunnerSupplier(SupplierRepo supplierRepo){
        return args -> {};
    }
}
