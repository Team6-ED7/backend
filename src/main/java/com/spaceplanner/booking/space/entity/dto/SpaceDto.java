package com.spaceplanner.booking.space.entity.dto;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceDto implements Serializable {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Floor is required")
    private Integer floor;

    @NotBlank(message = "Description is required")
    private String description;

    private Integer capacity = 1;

    private Boolean available = true;

    @NotBlank(message = "Type Space is required")
    private String typeSpace;

    private UUID codeUuid;
}
