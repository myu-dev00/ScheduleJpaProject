package org.example.schedulejpa.dto;

import lombok.Getter;
import org.example.schedulejpa.domain.Schedule;

//응답
@Getter
public class ScheduleResponseDto {
    private final Long id; //식별자
    private final String title; //제목
    private final String contents; //내용
    private final String username; //유저명

    public ScheduleResponseDto(Long id, String title, String contents, String username) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUsername()
        );
    }
}