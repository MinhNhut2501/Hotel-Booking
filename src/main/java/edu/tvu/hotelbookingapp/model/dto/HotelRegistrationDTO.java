package edu.tvu.hotelbookingapp.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRegistrationDTO {

    @NotBlank(message = "Hotel name cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L}\\p{M}\\p{N} ]+$", message = "Hotel name must only contain letters, numbers, and spaces")
    private String name;

    @Valid
    private AddressDTO addressDTO;

    @Valid
    private List<RoomDTO> roomDTOs = new ArrayList<>();

}
