package com.codecool.talee.DTO.auth;

import java.util.List;

public record AuthResponseDTO(String jwtToken, String userName, List<String> roles, String message) {
}
