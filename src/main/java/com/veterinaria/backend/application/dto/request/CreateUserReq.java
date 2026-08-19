package com.veterinaria.backend.application.dto.request;

import com.veterinaria.backend.domain.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserReq {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private Rol rol;

}
