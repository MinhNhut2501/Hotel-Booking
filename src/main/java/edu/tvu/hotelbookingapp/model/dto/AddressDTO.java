package edu.tvu.hotelbookingapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    @NotBlank(message = "Address line cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L}\\p{N},/ -]+$", message = "Address must only contain letters, numbers, commas, slashes, hyphens, and spaces")
    private String addressLine;

    @NotBlank(message = "City cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L}\\p{N}, ]+$", message = "Address must only contain letters, numbers, commas, and spaces")
    private String city;

    @NotBlank(message = "Country cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L}\\p{N}, ]+$", message = "Address must only contain letters, numbers, commas, and spaces")
    private String country;
}
