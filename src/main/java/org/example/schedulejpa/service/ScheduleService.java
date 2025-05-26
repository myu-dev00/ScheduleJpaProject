package org.example.schedulejpa.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpa.domain.Schedule;
import org.example.schedulejpa.domain.User;
import org.example.schedulejpa.dto.ScheduleResponseDto;
import org.example.schedulejpa.repository.ScheduleRepository;
import org.example.schedulejpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(String title, String contents, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID = " + userId));
        Schedule schedule = new Schedule(title, contents, user);
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.toDto(schedule);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::toDto)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleResponseDto.toDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto update(Long id, String title, String contents) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        schedule.update(title, contents);
        return ScheduleResponseDto.toDto(schedule);
    }

    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);
    }
}
