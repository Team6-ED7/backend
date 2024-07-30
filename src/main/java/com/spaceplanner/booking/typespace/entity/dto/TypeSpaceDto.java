package com.spaceplanner.booking.typespace.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeSpaceDto implements Serializable {

    private String name;
}
