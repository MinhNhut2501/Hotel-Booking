package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface CustomerService {

    Optional<Customer> findByUserId(Long userId);

    Optional<Customer> findByUsername(String username);
}
