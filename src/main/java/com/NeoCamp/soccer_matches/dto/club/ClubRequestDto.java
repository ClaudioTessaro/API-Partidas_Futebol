package com.neocamp.soccer_matches.dto.club;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubRequestDto {
    @NotBlank(message = "Field name is required")
    @Size(min = 3, max = 100, message = "Field name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Field state code is required")
    @Size(min = 2, max = 2, message = "Field state code must have exactly 2 characters")
    private String stateCode;

    @NotNull(message = "Field creation date is required")
    private LocalDate creationDate;

    @NotNull (message = "Field active is required")
    private Boolean active;
}
