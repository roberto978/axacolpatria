package com.rtoro.axacolpatria.customer.mapper;

import com.rtoro.axacolpatria.customer.dto.CustomerDTO;
import com.rtoro.axacolpatria.customer.entity.Customer;
import java.util.List;
import java.util.stream.Stream;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Roberto Toro
 */

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCIA= Mappers.getMapper(CustomerMapper.class);
    
    CustomerDTO mapToDto(Customer customer);
    
    Customer mapToEntity(CustomerDTO customerDTO);
    
    List<CustomerDTO> mapToDtoList(Stream<Customer> customers);
}
