package org.example.schedulejpa.dto;

import lombok.Getter;

//User요청Dto
@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
}