package com.rtoro.axacolpatria.customer.service;

import com.rtoro.axacolpatria.customer.dto.CustomerDTO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author Roberto Toro
 */

public interface CustomerService {

    List<CustomerDTO> find();
    CustomerDTO find(long customerId);
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO);
    boolean delete(long customerId);
}
