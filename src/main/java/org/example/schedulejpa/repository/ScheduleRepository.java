package org.example.schedulejpa.repository;


import org.example.schedulejpa.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //예외처리
    default Schedule findByIdOrElseThrow(Long id) {
        //id 옵셔널 설정
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다. ID = " + id));
    }
}