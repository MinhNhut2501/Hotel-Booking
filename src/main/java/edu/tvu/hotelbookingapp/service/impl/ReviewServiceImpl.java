package edu.tvu.hotelbookingapp.service.impl;

import edu.tvu.hotelbookingapp.model.Booking;
import edu.tvu.hotelbookingapp.model.Hotel;
import edu.tvu.hotelbookingapp.model.Review;
import edu.tvu.hotelbookingapp.model.dto.ReviewDTO;
import edu.tvu.hotelbookingapp.repository.BookingRepository;
import edu.tvu.hotelbookingapp.repository.HotelRepository;
import edu.tvu.hotelbookingapp.repository.ReviewRepository;
import edu.tvu.hotelbookingapp.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;

    @Override
    public void saveReview(ReviewDTO reviewDTO) {
        Booking booking = bookingRepository.findById(reviewDTO.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        Hotel hotel = hotelRepository.findById(reviewDTO.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        Review review = new Review();
        review.setBooking(booking);
        review.setHotel(hotel);
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setReviewDate(LocalDate.now());

        reviewRepository.save(review);
    }

    @Override
    public boolean existsByBookingId(Long bookingId) {
        return reviewRepository.existsByBookingId(bookingId);
    }

    @Override
    public ReviewDTO findByBookingId(Long bookingId) {
        return reviewRepository.findByBookingId(bookingId)
                .map(review -> {
                    ReviewDTO dto = new ReviewDTO();
                    dto.setBookingId(review.getBooking().getId());
                    dto.setHotelId(review.getHotel().getId());
                    dto.setRating(review.getRating());
                    dto.setComment(review.getComment());
                    return dto;
                })
                .orElse(null);
    }

}
