package com.triangle.authorization.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class RoleDto {

    /**
     * Имя
     */
    @NonNull
    private String name;
}
