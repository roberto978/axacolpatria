package com.rtoro.axacolpatria.customer.repository;

import com.rtoro.axacolpatria.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Roberto Toro
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
