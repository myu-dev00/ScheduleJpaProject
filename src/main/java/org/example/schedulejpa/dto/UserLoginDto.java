package org.example.schedulejpa.dto;

import lombok.Getter;

//로그인Dto
@Getter
public class UserLoginDto {
    private String email;
    private String password;
}