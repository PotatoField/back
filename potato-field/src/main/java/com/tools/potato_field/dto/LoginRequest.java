package com.tools.potato_field.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}