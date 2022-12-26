package me.elmajni.inventoryqueryservice.controllers;

import lombok.AllArgsConstructor;

import me.elmajni.commonapi.EventDataResponseDTO;
import me.elmajni.commonapi.GetAllCategoriesQuery;
import me.elmajni.commonapi.GetCategorieById;
import me.elmajni.commonapi.SubscribeToEventsQuery;
import me.elmajni.inventoryqueryservice.entities.Categorie;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/query/categories")
@AllArgsConstructor
@Service
public class CategoryQueryController {
    private QueryGateway queryGateway;


    @GetMapping(path = "/")
    public List<Categorie> getCategorie() {
        return queryGateway.query(new GetAllCategoriesQuery(),
                ResponseTypes.multipleInstancesOf(Categorie.class)).join();
    }

    @GetMapping(path = "/{id}")
    public Categorie getCategorie(@PathVariable String id) {
        return queryGateway.query(new GetCategorieById(id), Categorie.class).join();
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