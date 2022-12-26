package me.elmajni.inventorycommandsservice.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/inventory/categories")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CategoryCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createOwner(@RequestBody CategorieDTO request) {
        CompletableFuture<String> response = commandGateway.send(new CreateNewCategorieCommand(
                UUID.randomUUID().toString(),
                request
        ));
        return response;
    }

    @GetMapping("/eventStore/{id}")
    public Stream streamEvents(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }


    @PostMapping("/updateCategorie")
    public CompletableFuture<String> updateCategorie(@RequestBody CategorieDTO request){
        return this.commandGateway.send(new UpdateCategorieCommand(
                request.getId(),
                request
        ));
    }

    @PostMapping("/deleteCategorie")
    public CompletableFuture<String> deleteCategory(@RequestBody CategorieDTO request){
        return this.commandGateway.send(new DeleteCategorieCommand(
                request.getId(),
                request
        ));
    }
}