package com.spaceplanner.booking.typespace.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeSpaceDto implements Serializable {

    @NotNull(message = "Name is required")
    private String name;
}
