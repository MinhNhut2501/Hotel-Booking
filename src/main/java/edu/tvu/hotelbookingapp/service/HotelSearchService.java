package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.Hotel;
import edu.tvu.hotelbookingapp.model.dto.HotelAvailabilityDTO;

import java.time.LocalDate;
import java.util.List;

public interface HotelSearchService {

    List<HotelAvailabilityDTO> findAvailableHotelsByCityAndDate(String city, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO findAvailableHotelById(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO mapHotelToHotelAvailabilityDto(Hotel hotel, LocalDate checkinDate, LocalDate checkoutDate);
}
