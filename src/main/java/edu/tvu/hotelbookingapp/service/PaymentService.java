package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.Booking;
import edu.tvu.hotelbookingapp.model.Payment;
import edu.tvu.hotelbookingapp.model.dto.BookingInitiationDTO;

public interface PaymentService {

    Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking);
}
