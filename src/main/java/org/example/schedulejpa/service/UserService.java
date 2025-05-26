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

    //User 생성
    public UserResponseDto create(UserRequestDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail());
        return UserResponseDto.from(userRepository.save(user));
    }

    //User 전체조회
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    //User 단건조회
    public UserResponseDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + id));
    }

    //User 수정
    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + id));
        user.update(dto.getUsername(), dto.getEmail());
        return UserResponseDto.from(user);
    }

    //User delete
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + id));
        userRepository.delete(user);
    }
}
