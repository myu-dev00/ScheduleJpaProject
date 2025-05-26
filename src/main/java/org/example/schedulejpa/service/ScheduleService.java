package org.example.schedulejpa.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpa.domain.Schedule;
import org.example.schedulejpa.dto.CreateScheduleRequestDto;
import org.example.schedulejpa.dto.ScheduleResponseDto;
import org.example.schedulejpa.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, String username) {
        Schedule schedule = new Schedule(title, contents, username);
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
