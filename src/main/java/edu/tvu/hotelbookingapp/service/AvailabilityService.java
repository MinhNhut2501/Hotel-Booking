package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.dto.RoomSelectionDTO;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {

    Integer getMinAvailableRooms(Long roomId, LocalDate checkinDate, LocalDate checkoutDate);

    void updateAvailabilities(long hotelId, LocalDate checkinDate, LocalDate checkoutDate, List<RoomSelectionDTO> roomSelections);

}
