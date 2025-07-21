package edu.tvu.hotelbookingapp.controller;

import edu.tvu.hotelbookingapp.model.dto.BookingDTO;
import edu.tvu.hotelbookingapp.model.dto.ReviewDTO;
import edu.tvu.hotelbookingapp.service.BookingService;
import edu.tvu.hotelbookingapp.service.ReviewService;
import edu.tvu.hotelbookingapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final UserService userService;
    private final BookingService bookingService;
    private final ReviewService reviewService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "customer/dashboard";
    }

    @GetMapping("/bookings")
    public String listBookings(Model model, RedirectAttributes redirectAttributes) {
        try {
            Long customerId = getCurrentCustomerId();
            List<BookingDTO> bookingDTOs = bookingService.findBookingsByCustomerId(customerId);
            if (bookingDTOs == null) {
                bookingDTOs = List.of(); // List.of() => list rỗng, an toàn
            }
            model.addAttribute("bookings", bookingDTOs);
            model.addAttribute("noBookingsFound", bookingDTOs.isEmpty());

            return "customer/bookings";
        } catch (EntityNotFoundException e) {
            log.error("No customer found with the provided ID", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Customer not found. Please log in again.");
            return "redirect:/login";
        } catch (Exception e) {
            log.error("An error occurred while listing bookings", e);
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            return "redirect:/";
        }
    }

    @GetMapping("/bookings/{id}")
    public String viewBookingDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Long customerId = getCurrentCustomerId();
            BookingDTO bookingDTO = bookingService.findBookingByIdAndCustomerId(id, customerId);
            model.addAttribute("bookingDTO", bookingDTO);

            LocalDate checkinDate = bookingDTO.getCheckinDate();
            LocalDate checkoutDate = bookingDTO.getCheckoutDate();
            long durationDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
            model.addAttribute("days", durationDays);
            ReviewDTO reviewDTO = reviewService.findByBookingId(id);
            model.addAttribute("reviewDTO", reviewDTO);

            return "customer/bookings-details";
        } catch (EntityNotFoundException e) {
            log.error("No booking found with the provided ID", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Booking not found. Please try again later.");
            return "redirect:/customer/bookings";
        } catch (Exception e) {
            log.error("An error occurred while displaying booking details", e);
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            return "redirect:/";
        }
    }

    private Long getCurrentCustomerId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByUsername(username).getCustomer().getId();
    }

    @GetMapping("/bookings/{id}/review")
    public String showReviewForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        // Kiểm tra booking tồn tại
        BookingDTO booking = bookingService.findBookingById(id);
        if (booking == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Booking not found.");
            return "redirect:/customer/bookings";
        }

        // Kiểm tra xem đã có review cho booking này chưa
        if (reviewService.existsByBookingId(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "You have already reviewed this booking.");
            return "redirect:/customer/bookings";
        }

        // Kiểm tra xem đã qua ngày checkout chưa
        LocalDate checkoutDate = booking.getCheckoutDate();
        if (LocalDate.now().isBefore(checkoutDate)) {
            redirectAttributes.addFlashAttribute("errorMessage", "You can only review after your stay is completed.");
            return "redirect:/customer/bookings";
        }

        // Chuẩn bị dữ liệu cho form
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setBookingId(id);
        reviewDTO.setHotelId(booking.getHotelId()); // Giả định BookingDTO có getHotel()
        model.addAttribute("reviewDTO", reviewDTO);
        model.addAttribute("hotelName", booking.getHotelName());
        return "customer/review-form";
    }

    @PostMapping("/bookings/{id}/review")
    public String submitReview(@PathVariable Long id, @Valid @ModelAttribute ReviewDTO reviewDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "customer/review-form";
        }

        try {
            reviewService.saveReview(reviewDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Thank you for your review!");
        } catch (Exception e) {
            log.error("Error submitting review for booking ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while submitting your review.");
            return "customer/review-form";
        }
        return "redirect:/customer/bookings";
    }

}