package org.example.schedulejpa.dto;

import lombok.Getter;
import org.example.schedulejpa.domain.Schedule;

//Schedule요청Dto
@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;

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
                schedule.getUser().getUsername() //User의 Username을 가져옴.
        );
    }
}