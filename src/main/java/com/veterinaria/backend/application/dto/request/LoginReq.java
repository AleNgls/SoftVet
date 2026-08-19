package com.veterinaria.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReq {

    @NotBlank
    String username;
    @NotBlank
    String password;

}
