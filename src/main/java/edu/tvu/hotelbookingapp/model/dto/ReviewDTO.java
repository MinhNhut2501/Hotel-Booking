package edu.tvu.hotelbookingapp.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long bookingId;
    private Long hotelId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @Size(max = 500, message = "Comment must not exceed 500 characters")
    private String comment;
}
