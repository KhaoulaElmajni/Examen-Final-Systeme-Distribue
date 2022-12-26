package me.elmajni.customerqueryservice.controllers;

import me.elmajni.commonapi.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@CrossOrigin("*")
@RestController
@RequestMapping("/customers/query")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    public CustomerQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping("/all")
    public CompletableFuture<List<CustomerDTO>> allCustomers(){
        return queryGateway.query(
                new GetAllCustomersQuery()
                , ResponseTypes.multipleInstancesOf(CustomerDTO.class)
        );
    }

    @GetMapping("/byId/{id}")
    public CompletableFuture<CustomerDTO> geRadarById(@PathVariable String id){
        return queryGateway.query(
                new GetCustomerById(id)
                , ResponseTypes.instanceOf(CustomerDTO.class)
        );
    }
    @GetMapping("/byNom/{nom}")
    public CompletableFuture<CustomerDTO> geRadarByNom(@PathVariable String nom){
        return queryGateway.query(
                new GetCustomerByNom(nom)
                , ResponseTypes.instanceOf(CustomerDTO.class)
        );
    }


    @GetMapping(value = "/subscribeToEvents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDataResponseDTO> subscribe(){
        SubscriptionQueryResult<List<EventDataResponseDTO>, EventDataResponseDTO> queryResult = this.queryGateway.subscriptionQuery(
                new SubscribeToEventsQuery(),
                ResponseTypes.multipleInstancesOf(EventDataResponseDTO.class),
                ResponseTypes.instanceOf(EventDataResponseDTO.class)
        );
        return queryResult.initialResult().flatMapMany(Flux::fromIterable).concatWith(queryResult.updates());
    }
}
