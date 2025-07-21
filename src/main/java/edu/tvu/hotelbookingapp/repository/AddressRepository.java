package edu.tvu.hotelbookingapp.repository;

import edu.tvu.hotelbookingapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
