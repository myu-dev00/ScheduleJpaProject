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

    //생성
    public ScheduleResponseDto save(String title, String contents, String username) {
        Schedule schedule = new Schedule(title, contents, username);
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.toDto(schedule);
    }

    //전체조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::toDto)
                .collect(Collectors.toList());
    }

    //단건 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleResponseDto.toDto(schedule);
    }

    //수정
    @Transactional
    public ScheduleResponseDto update(Long id, String title, String contents) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        schedule.update(title, contents);
        return ScheduleResponseDto.toDto(schedule);
    }

    //삭제
    @Transactional
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);
    }
}
