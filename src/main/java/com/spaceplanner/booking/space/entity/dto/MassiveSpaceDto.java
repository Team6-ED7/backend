package com.spaceplanner.booking.space.entity.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.io.Serializable;

@Data
public class MassiveSpaceDto implements Serializable {

    @NotNull(message = "The floor number must not be null")
    @Min(value = 1, message = "Floor must be greater than 0")
    private Integer floor;

    @NotNull(message = "The number of spaces must not be null")
    @Min(value = 1, message = "Number of spaces must be greater than 0")
    @Max(value = 100, message = "The maximum number of spaces to create is 100")
    private Integer totalSpace;

    @NotBlank(message = "Type Space is required")
    private String typeSpace;

}
