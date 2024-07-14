package com.example.bookservice.common.DTOs;

import com.example.bookservice.common.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role) {
}
