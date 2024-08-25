package com.spaceplanner.booking.space.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceDto implements Serializable {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull
    @Positive(message = "Floor is required")
    private Integer floor;

    @NotBlank(message = "Description is required")
    private String description;

    @Builder.Default
    private Integer capacity = 1;

    @Builder.Default
    private Boolean available = true;

    @NotBlank(message = "Type Space is required")
    private String typeSpace;
}
