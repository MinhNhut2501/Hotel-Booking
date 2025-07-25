package edu.tvu.hotelbookingapp.repository;

import edu.tvu.hotelbookingapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByBookingId(Long bookingId);
    Optional<Review> findByBookingId(Long bookingId);
}
