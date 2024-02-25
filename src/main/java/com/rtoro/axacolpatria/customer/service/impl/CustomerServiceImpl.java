package com.rtoro.axacolpatria.customer.service.impl;

import com.rtoro.axacolpatria.customer.dto.CustomerDTO;
import com.rtoro.axacolpatria.customer.entity.Customer;
import com.rtoro.axacolpatria.customer.exceptions.BusinessException;
import com.rtoro.axacolpatria.customer.mapper.CustomerMapper;
import com.rtoro.axacolpatria.customer.repository.CustomerRepository;
import com.rtoro.axacolpatria.customer.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roberto Toro
 */

@Service
public class CustomerServiceImpl implements CustomerService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> find() {
        
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        return CustomerMapper.INSTANCIA.mapToDtoList(list.stream());
    }

    @Override
    public CustomerDTO find(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return CustomerMapper.INSTANCIA.mapToDto(customer);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        validateAge(customerDTO.getAge());
        
        Customer customer = CustomerMapper.INSTANCIA.mapToEntity(customerDTO);
        customer = customerRepository.save(customer);
        
        return CustomerMapper.INSTANCIA.mapToDto(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        validateId(customerDTO.getId());
        validateAge(customerDTO.getAge());
        Customer customer = CustomerMapper.INSTANCIA.mapToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return CustomerMapper.INSTANCIA.mapToDto(customer);
    }

    @Override
    public boolean delete(long customerId) {
        validateId(customerId);
        customerRepository.deleteById(customerId);
        return true;
    }

    
    private void validateAge(int age){
        if(age < 1){
            throw new BusinessException("Age","Por favor, ingrese la edad");
        }
        
        if(age > 99){
            throw new BusinessException("Age","La edad debe ser máxima 99 años");
        }
    }
    
    private void validateId(long id){
        if(id < 1){
            throw new BusinessException("Id","No se puede procesar el registro");
        }
    }
   
}
