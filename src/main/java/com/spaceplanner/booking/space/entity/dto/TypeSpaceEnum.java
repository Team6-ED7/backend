package com.spaceplanner.booking.space.entity.dto;

public enum TypeSpaceEnum {
    PDT("Puesto de Trabajo"),
    SALA("Sala de Reuniones"),
    AUD("Auditorio"),
    OFI("Oficina");

    private final String typeSpace;

    TypeSpaceEnum(String typeSpace) {
        this.typeSpace = typeSpace;
    }

    public String getTypeSpace() {
        return typeSpace;
    }






}
