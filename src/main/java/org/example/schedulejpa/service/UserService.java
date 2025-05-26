package org.example.schedulejpa.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpa.domain.User;
import org.example.schedulejpa.dto.UserRequestDto;
import org.example.schedulejpa.dto.UserResponseDto;
import org.example.schedulejpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public UserResponseDto create(UserRequestDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        return UserResponseDto.from(userRepository.save(user));
    }

    // 전체 조회
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    // 단건 조회
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + id));
        return UserResponseDto.from(user);
    }

    // 수정
    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + id));
        user.update(dto.getUsername(), dto.getEmail(), dto.getPassword());
        return UserResponseDto.from(user);
    }

    // 삭제
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + id));
        userRepository.delete(user);
    }
}