package me.elmajni.ordercommandsservice;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderCommandsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCommandsServiceApplication.class, args);
    }

    @Bean
    public SimpleCommandBus axonServerCommandBus(){
        return SimpleCommandBus.builder().build();
    }

}
