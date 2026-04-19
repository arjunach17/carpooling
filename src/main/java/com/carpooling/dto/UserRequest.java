package com.carpooling.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone number is invalid")
    private String phone;
}
