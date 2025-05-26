package org.example.schedulejpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulejpa.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    //JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    //생성자
    public Schedule(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    //수정 데이터
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}