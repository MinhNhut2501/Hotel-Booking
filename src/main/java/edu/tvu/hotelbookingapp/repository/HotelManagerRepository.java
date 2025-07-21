package edu.tvu.hotelbookingapp.repository;

import edu.tvu.hotelbookingapp.model.HotelManager;
import edu.tvu.hotelbookingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelManagerRepository extends JpaRepository<HotelManager, Long> {

    Optional<HotelManager> findByUser(User user);
}
