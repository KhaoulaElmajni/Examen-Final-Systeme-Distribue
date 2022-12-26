package me.elmajni.inventorycommandsservice.controllers;

import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/inventory/produits")
@CrossOrigin("*")
public class ProduitCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public ProduitCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    @PostMapping("/create")
    public CompletableFuture<String> addNewVehicleCommand(@RequestBody ProduitDTO request){
        return this.commandGateway.send(new CreateNewProduitCommand(
                UUID.randomUUID().toString(),
                request
        ));
    }
    @GetMapping("/eventStore/{id}")
    public Stream streamEvents(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }

    @PostMapping("/updateProduit")
    public CompletableFuture<String> upodateProduit(@RequestBody ProduitDTO request){
        return this.commandGateway.send(new UpdateProduitCommand(
                request.getId(),
                request
        ));
    }

    @PostMapping("/deleteProduit")
    public CompletableFuture<String> deleteProduit(@RequestBody ProduitDTO request){
        return this.commandGateway.send(new DeleteProduitCommand(
                request.getId(),
                request
        ));
    }

}
