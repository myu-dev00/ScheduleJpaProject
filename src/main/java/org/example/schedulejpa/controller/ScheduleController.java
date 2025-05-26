package org.example.schedulejpa.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpa.dto.CreateScheduleRequestDto;
import org.example.schedulejpa.dto.ScheduleResponseDto;
import org.example.schedulejpa.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.save(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getUserId()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> schedules = scheduleService.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.findById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(
            @PathVariable Long id,
            @RequestBody CreateScheduleRequestDto requestDto
    ) {
        ScheduleResponseDto responseDto = scheduleService.update(
                id,
                requestDto.getTitle(),
                requestDto.getContents()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
