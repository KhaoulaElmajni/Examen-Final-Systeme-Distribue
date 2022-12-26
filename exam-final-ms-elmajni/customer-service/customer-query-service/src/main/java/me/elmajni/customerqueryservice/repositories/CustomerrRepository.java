package me.elmajni.customerqueryservice.repositories;

import me.elmajni.customerqueryservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerrRepository extends JpaRepository<Customer,String> {
}
