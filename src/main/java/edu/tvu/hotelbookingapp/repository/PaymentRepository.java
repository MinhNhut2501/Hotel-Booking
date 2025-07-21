package edu.tvu.hotelbookingapp.repository;

import edu.tvu.hotelbookingapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
