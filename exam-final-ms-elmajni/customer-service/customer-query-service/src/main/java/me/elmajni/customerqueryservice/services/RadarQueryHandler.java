package me.elmajni.customerqueryservice.services;

import me.elmajni.commonapi.*;
import me.elmajni.customerqueryservice.entities.Customer;
import me.elmajni.customerqueryservice.mappers.CustomerMappers;
import me.elmajni.customerqueryservice.repositories.CustomerrRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RadarQueryHandler {
    private CustomerrRepository customerrRepository;
    private CustomerMappers customerMappers;


    public RadarQueryHandler(CustomerrRepository customerrRepository, CustomerMappers customerMappers) {
        this.customerrRepository = customerrRepository;
        this.customerMappers = customerMappers;
    }

    @QueryHandler
    public List<CustomerDTO>  handler(GetAllCustomersQuery query){
        List<Customer> allCustomers = customerrRepository.findAll();
        return allCustomers.stream().map(customer->customerMappers.from(customer))
                .collect(Collectors.toList());
    }


    @QueryHandler
    public CustomerDTO  handler(GetCustomerById query){
        Customer customer= customerrRepository.findById(query.getRadarId())
                .orElseThrow(()->new RuntimeException("Customer Not found"));
        CustomerDTO customerDTO=customerMappers.from(customer);

        return customerDTO;
    }


}
