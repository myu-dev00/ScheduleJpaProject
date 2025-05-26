package org.example.schedulejpa.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpa.domain.User;
import org.example.schedulejpa.dto.UserLoginDto;
import org.example.schedulejpa.dto.UserRequestDto;
import org.example.schedulejpa.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserRequestDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if (user == null || !user.checkPassword(dto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok("Login successful");
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody UserLoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        if (!user.checkPassword(dto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
