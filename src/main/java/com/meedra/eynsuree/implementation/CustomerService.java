package com.meedra.eynsuree.implementation;


import com.meedra.eynsuree.model.Customer;
import com.meedra.eynsuree.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;


    public Optional<Customer>  fetchCustomer(String email){

        return customerRepository.findByEmail(email);
    }
}
