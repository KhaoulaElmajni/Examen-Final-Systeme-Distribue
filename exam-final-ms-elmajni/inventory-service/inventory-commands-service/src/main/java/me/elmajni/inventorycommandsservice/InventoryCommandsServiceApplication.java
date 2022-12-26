package me.elmajni.inventorycommandsservice;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryCommandsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryCommandsServiceApplication.class, args);
    }

    @Bean
    public SimpleCommandBus axonServerCommandBus(){
        return SimpleCommandBus.builder().build();
    }

}
