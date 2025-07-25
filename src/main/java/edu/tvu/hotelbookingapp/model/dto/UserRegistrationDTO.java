package edu.tvu.hotelbookingapp.model.dto;

import edu.tvu.hotelbookingapp.model.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDTO {

    @NotBlank(message = "Email address cannot be empty")
    @Email(message = "Invalid email address")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 to 20 characters")
    private String password;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L} ]+$", message = "Name must only contain letters and spaces")
    private String name;

    @NotBlank(message = "Last name cannot be empty")
    @Pattern(regexp = "^(?!\\s*$)[\\p{L} ]+$", message = "Name must only contain letters and spaces")
    private String lastName;

    private RoleType roleType;

}
