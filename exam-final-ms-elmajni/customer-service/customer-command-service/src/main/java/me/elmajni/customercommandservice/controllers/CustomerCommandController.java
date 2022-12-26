package me.elmajni.customercommandservice.controllers;

import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.CreateNewCustomerCommand;
import me.elmajni.commonapi.CustomerDTO;
import me.elmajni.commonapi.DeleteCustomerCommand;
import me.elmajni.commonapi.UpdateCustomerCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/customers/commands")
@CrossOrigin("*")
public class CustomerCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public CustomerCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    @PostMapping("/create")
    public CompletableFuture<String> addNewRadarCommand(@RequestBody CustomerDTO request){
        return this.commandGateway.send(new CreateNewCustomerCommand(
                UUID.randomUUID().toString(),
                request
        ));
    }
    @GetMapping("/eventStore/{id}")
    public Stream streamEvents(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }

    @PostMapping("/updateCustomer")
    public CompletableFuture<String> changeRadarStatus(@RequestBody CustomerDTO request){
        return this.commandGateway.send(new UpdateCustomerCommand(
                request.getId(),
                request
        ));
    }

    @PostMapping("/deleteCustomer")
    public CompletableFuture<String> changeSpeedLimit(@RequestBody CustomerDTO request){
        return this.commandGateway.send(new DeleteCustomerCommand(
                request.getId(),
                request
        ));
    }

}
