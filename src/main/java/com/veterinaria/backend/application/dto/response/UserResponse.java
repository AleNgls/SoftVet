package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

    long id;
    String username;
    String mail;

    public static UserResponse from(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .mail(user.getEmail())
                .build();
    }

}
