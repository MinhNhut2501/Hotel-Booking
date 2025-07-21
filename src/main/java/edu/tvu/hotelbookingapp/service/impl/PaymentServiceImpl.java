package edu.tvu.hotelbookingapp.service.impl;

import edu.tvu.hotelbookingapp.model.Booking;
import edu.tvu.hotelbookingapp.model.Payment;
import edu.tvu.hotelbookingapp.model.dto.BookingInitiationDTO;
import edu.tvu.hotelbookingapp.model.enums.Currency;
import edu.tvu.hotelbookingapp.model.enums.PaymentMethod;
import edu.tvu.hotelbookingapp.model.enums.PaymentStatus;
import edu.tvu.hotelbookingapp.repository.PaymentRepository;
import edu.tvu.hotelbookingapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking) {
        Payment payment = Payment.builder()
                .booking(booking)
                .totalPrice(bookingInitiationDTO.getTotalPrice())
                .paymentStatus(PaymentStatus.COMPLETED) // Assuming the payment is completed
                .paymentMethod(PaymentMethod.CREDIT_CARD) // Default to CREDIT_CARD
                .currency(Currency.USD) // Default to USD
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved with transaction ID: {}", savedPayment.getTransactionId());

        return savedPayment;
    }
}
