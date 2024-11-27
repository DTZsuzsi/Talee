package com.codecool.DTO.auth;

import java.util.List;

public record AuthResponseDTO(String jwtToken, String userName, List<String> roles) {
}
