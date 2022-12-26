package me.elmajni.inventorycommandsservice.aggregates;

import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class CategorieAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private String description;


    public CategorieAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public CategorieAggregate(CreateNewCategorieCommand command) {
        AggregateLifecycle.apply(new CategorieCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(CategorieCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getPayload().getNom();
        this.description = event.getPayload().getDescription();
    }

    @CommandHandler
    public void handle(UpdateCategorieCommand command){
        log.info("UpdateCategorieCommand received");
        AggregateLifecycle.apply(new CategorieUpdatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(CategorieUpdatedEvent event){
        log.info("CategorieUpdatedEvent to"+ event.getPayload()+" Occured");
        this.id =event.getId();
    }

    @CommandHandler
    public void handle(DeleteCategorieCommand command){
        log.info("DeleteCategorieCommand received");
        AggregateLifecycle.apply(new CategorieDeletedEvent(
                command.getId(),
                command.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(CategorieDeletedEvent event){
        log.info("CustomerDeletedEvent to"+ event.getPayload()+" Occured");
        this.id =event.getId();
    }

}
