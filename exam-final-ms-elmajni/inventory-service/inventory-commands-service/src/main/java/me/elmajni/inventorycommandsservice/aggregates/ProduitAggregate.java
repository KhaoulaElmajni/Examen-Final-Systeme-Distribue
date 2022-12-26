package me.elmajni.inventorycommandsservice.aggregates;

import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class ProduitAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private String description;

    private double prix;
    private int qte;
    private ProduitEtat etat;


    public ProduitAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public ProduitAggregate(CreateNewProduitCommand command) {

        AggregateLifecycle.apply(new ProduitCreatedEvent(
                command.getId(),
                command.getPayload()));
    }


    @EventSourcingHandler
    public void on(ProduitCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getPayload().getNom();
        this.description = event.getPayload().getDescription();
        this.prix = event.getPayload().getPrix();
        this.qte = event.getPayload().getQuantity();
        this.etat = event.getPayload().getEtat();
    }


    @CommandHandler
    public void handle(UpdateProduitCommand command){
        log.info("UpdateCategorieCommand received");
        AggregateLifecycle.apply(new ProduitUpdatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(ProduitUpdatedEvent event){
        log.info("ProduitUpdatedEvent to"+ event.getPayload()+" Occured");
        this.id =event.getId();
    }

    @CommandHandler
    public void handle(DeleteProduitCommand command){
        log.info("DeleteProduitCommand received");
        AggregateLifecycle.apply(new ProduitDeletedEvent(
                command.getId(),
                command.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(ProduitDeletedEvent event){
        log.info("ProduitDeletedEvent to"+ event.getPayload()+" Occured");
        this.id =event.getId();
    }
}
