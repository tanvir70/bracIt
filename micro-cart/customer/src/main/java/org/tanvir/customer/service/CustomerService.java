package org.tanvir.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tanvir.customer.model.Customer;
import org.tanvir.customer.repository.CustomerRepository;

import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           KafkaTemplate<String, String> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        kafkaTemplate.send("customer-events",
                "Customer created: " + savedCustomer.getId());
        return savedCustomer;
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
}
