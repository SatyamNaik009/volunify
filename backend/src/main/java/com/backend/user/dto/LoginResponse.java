package com.backend.user.dto;

public record LoginResponse(String name,Long userId,String phoneNumber,String message) {
}
