package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.Hotel;
import edu.tvu.hotelbookingapp.model.dto.HotelDTO;
import edu.tvu.hotelbookingapp.model.dto.HotelRegistrationDTO;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    Hotel saveHotel(HotelRegistrationDTO hotelRegistrationDTO);

    HotelDTO findHotelDtoByName(String name);

    HotelDTO findHotelDtoById(Long id);

    Optional<Hotel> findHotelById(Long id);

    List<HotelDTO> findAllHotels();

    HotelDTO updateHotel(HotelDTO hotelDTO);

    void deleteHotelById(Long id);

    List<Hotel> findAllHotelsByManagerId(Long managerId);

    List<HotelDTO> findAllHotelDtosByManagerId(Long managerId);

    HotelDTO findHotelByIdAndManagerId(Long hotelId, Long managerId);

    HotelDTO updateHotelByManagerId(HotelDTO hotelDTO, Long managerId);

    void deleteHotelByIdAndManagerId(Long hotelId, Long managerId);

    HotelDTO mapHotelToHotelDto(Hotel hotel);

}
