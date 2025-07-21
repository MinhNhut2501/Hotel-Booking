package edu.tvu.hotelbookingapp.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class HotelDTO {

    private Long id;

    @NotBlank(message = "Hotel name cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L}\\p{M}\\p{N} ]+$", message = "Hotel name must only contain letters, numbers, and spaces")
    private String name;

    @Valid
    private AddressDTO addressDTO;

    @Valid
    private List<RoomDTO> roomDTOs = new ArrayList<>();

    private String managerUsername;

}
