package com.harist.belajarspring.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer newCustomer) {
        return this.customerRepository.save(newCustomer);
    }

    public List<Customer> getCustomer() {
        return this.customerRepository.findAll();
    }

    public Customer findCustomerId(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        throw new CustomerNotFoundException();
    }

    public Customer findCustomer(Customer customer) {
        return this.findCustomerId(customer.getId());
    }
}
