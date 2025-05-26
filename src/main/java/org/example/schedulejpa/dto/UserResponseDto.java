package org.example.schedulejpa.dto;

import lombok.Getter;
import org.example.schedulejpa.domain.User;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}