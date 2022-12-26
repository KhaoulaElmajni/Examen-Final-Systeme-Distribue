package me.elmajni.customercommandservice.aggregates;

import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private String adresse;
    private String email;
    private String tel;

    public CustomerAggregate() {
    }
    @CommandHandler
    public CustomerAggregate(CreateNewCustomerCommand command) {
        log.info("CreateNewCustomerCommand received");
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        log.info("CustomerCreatedEvent occured");
        this.id =event.getId();
        this.nom =event.getPayload().getNom();
        this.adresse=event.getPayload().getAdresse();
        this.tel =event.getPayload().getTel();
        this.email=event.getPayload().getEmail();
    }
    @CommandHandler
    public void handle(UpdateCustomerCommand command){
        log.info("UpdateCustomerCommand received");
        AggregateLifecycle.apply(new CustomerUpdatedEvent(
           command.getId(),
           command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event){
        log.info("DeleteCustomerCommand to"+ event.getPayload()+" Occured");
        this.id =event.getId();
    }

    @CommandHandler
    public void handle(DeleteCustomerCommand command){
        log.info("DeleteCustomerCommand received");
        AggregateLifecycle.apply(new CustomerDeletedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerDeletedEvent event){
        log.info("CustomerDeletedEvent to"+ event.getPayload()+" Occured");
        this.id =event.getId();
    }

}
