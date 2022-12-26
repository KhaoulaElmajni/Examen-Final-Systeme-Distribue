package me.elmajni.inventoryqueryservice.controllers;

import lombok.AllArgsConstructor;

import me.elmajni.commonapi.EventDataResponseDTO;
import me.elmajni.commonapi.GetAllProduitsQuery;
import me.elmajni.commonapi.GetProduitById;
import me.elmajni.commonapi.SubscribeToEventsQuery;
import me.elmajni.inventoryqueryservice.entities.Produit;
import me.elmajni.inventoryqueryservice.repositories.ProduitRepository;
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
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/produits")
@AllArgsConstructor
@Service
public class ProduitQueryController {

        private QueryGateway queryGateway;
    private final ProduitRepository produitRepository;


    @GetMapping(path = "/all")
        public CompletableFuture<List<Produit>> getProduits() {

            return queryGateway.query(
                    new GetAllProduitsQuery()
                    , ResponseTypes.multipleInstancesOf(Produit.class)
            );
        }


        @GetMapping(path = "/{id}")
        public Produit getProduit(@PathVariable String id) {
            return queryGateway.query(new GetProduitById(id), Produit.class).join();
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
