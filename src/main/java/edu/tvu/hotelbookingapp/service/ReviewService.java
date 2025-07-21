package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.dto.ReviewDTO;

public interface ReviewService {
    void saveReview(ReviewDTO reviewDTO);
    boolean existsByBookingId(Long bookingId);
    ReviewDTO findByBookingId(Long bookingId);
}
