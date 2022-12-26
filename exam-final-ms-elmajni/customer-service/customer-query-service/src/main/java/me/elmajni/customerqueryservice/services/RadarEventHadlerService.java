package me.elmajni.customerqueryservice.services;

import lombok.extern.slf4j.Slf4j;
import me.elmajni.commonapi.*;

import me.elmajni.customerqueryservice.entities.Customer;
import me.elmajni.customerqueryservice.mappers.CustomerMappers;
import me.elmajni.customerqueryservice.repositories.CustomerrRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class RadarEventHadlerService {
    private CustomerrRepository customerrRepository;
    private CustomerMappers customerMappers;
    private QueryUpdateEmitter queryUpdateEmitter;

    public RadarEventHadlerService(CustomerrRepository customerrRepository,
                                   CustomerMappers customerMappers,
                                   QueryUpdateEmitter queryUpdateEmitter) {
        this.customerrRepository = customerrRepository;
        this.customerMappers = customerMappers;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event, EventMessage<CustomerCreatedEvent> eventMessage){
        log.info("*************** Query Event handler **************");
        log.info("CustomerCreatedEvent occured");
        Customer customer= customerMappers.from(event.getPayload());
        customer.setId(event.getId());
        customerrRepository.save(customer);
        event.getPayload().setId(event.getId());
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);

    }

    @EventHandler
    public void on(CustomerUpdatedEvent event){
        log.info("*************** Query Event handler **************");
        log.info("CustomerUpdatedEvent occured");
        Customer customer= customerrRepository.findById(event.getId())
                .orElseThrow(()->new RuntimeException("Customer not found"));
        customer.setId(event.getPayload().getId());
        customer.setNom(event.getPayload().getNom());
        customer.setAdresse(event.getPayload().getAdresse());
        customer.setTel(event.getPayload().getTel());
        customer.setEmail(event.getPayload().getEmail());

        customerrRepository.save(customer);
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);
    }


    @EventHandler
    public void on(CustomerDeletedEvent event){
        log.info("*************** Query Event handler **************");
        log.info("CustomerDeletedEvent occured");
        Customer customer= customerrRepository.findById(event.getId())
                .orElseThrow(()->new RuntimeException("Customer not found"));
        customer.setId(event.getPayload().getId());
        customer.setNom(event.getPayload().getNom());
        customer.setAdresse(event.getPayload().getAdresse());
        customer.setTel(event.getPayload().getTel());
        customer.setEmail(event.getPayload().getEmail());

        customerrRepository.delete(customer);
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);
    }


}
