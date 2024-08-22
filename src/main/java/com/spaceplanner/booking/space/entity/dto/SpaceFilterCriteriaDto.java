package com.spaceplanner.booking.space.entity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpaceFilterCriteriaDto implements Serializable {

    @NotNull(message = "Floor number is required.")
    @Min(value = 1, message = "The floor number is at least 1.")
    private Integer floor;

    @NotNull(message = "Space availability is mandatory.")
    private Boolean available;

    private String typeSpace;

}
