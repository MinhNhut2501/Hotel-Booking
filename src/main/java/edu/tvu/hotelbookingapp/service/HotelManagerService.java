package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.HotelManager;
import edu.tvu.hotelbookingapp.model.User;

public interface HotelManagerService {

    HotelManager findByUser(User user);

}
