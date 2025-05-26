package org.example.schedulejpa.dto;


import lombok.Getter;

//Schedule응답Dto
@Getter
public class CreateScheduleRequestDto {
    private final String title; //제목
    private final String contents; //내용
    private final Long userId; //User Entity 참조 식별자

    public CreateScheduleRequestDto(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}