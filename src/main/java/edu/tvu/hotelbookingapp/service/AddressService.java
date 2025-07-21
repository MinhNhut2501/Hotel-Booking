package edu.tvu.hotelbookingapp.service;

import edu.tvu.hotelbookingapp.model.Address;
import edu.tvu.hotelbookingapp.model.dto.AddressDTO;

public interface AddressService {

    Address saveAddress(AddressDTO addressDTO);

    AddressDTO findAddressById(Long id);

    Address updateAddress(AddressDTO addressDTO);

    void deleteAddress(Long id);

    Address mapAddressDtoToAddress(AddressDTO dto);

    AddressDTO mapAddressToAddressDto(Address address);


}
