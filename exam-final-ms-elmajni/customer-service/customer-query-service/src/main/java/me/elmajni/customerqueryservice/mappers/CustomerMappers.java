package me.elmajni.customerqueryservice.mappers;


import me.elmajni.commonapi.CustomerDTO;
import me.elmajni.customerqueryservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMappers {
    Customer from(CustomerDTO customerDTO);
    CustomerDTO from(Customer customer);

}
