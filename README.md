# 📅 Schedule Management Application (JPA 기반)

## 📌 프로젝트 개요

* Spring Boot와 Spring Data JPA를 활용한 **일정 관리 어플리케이션**
* 회원가입/로그인 기능을 포함하고 있으며, 회원별로 일정 데이터를 CRUD할 수 있음
* **MySQL 연동**을 통한 실 데이터베이스 기반의 작업
* JPA 기반의 `@Entity`, `DTO`, `Repository`, `Service`, `Controller` 등의 구조로 설계되어 확장성과 유지보수 용이

---

## ⚙️ 사용 기술

| 기술 스택 | 설명 |
|-----------|------|
| Java 17 | 프로젝트 개발 언어 |
| Spring Boot 3.5 | 프레임워크 및 애플리케이션 구동 |
| Spring Data JPA | ORM을 통한 데이터베이스 연동 |
| MySQL | 관계형 데이터베이스 |
| Lombok | 반복되는 코드 제거를 위한 어노테이션 사용 |
| Spring Web | REST API 제공 |
| Spring Boot  | 개발 편의성 향상 |
| JPA Auditing | 생성일/수정일 자동 처리 |

---

## 📁 프로젝트 구조

```
schedule/
├── controller/
│   ├── ScheduleController.java       // 일정 관련 REST API
│   └── UserController.java           // 회원가입, 로그인, 회원 삭제
├── domain/
│   ├── Schedule.java                 // 일정 Entity
│   └── User.java                     // 사용자 Entity
├── dto/
│   ├── CreateScheduleRequestDto.java // 일정 등록 요청 DTO
│   ├── ScheduleResponseDto.java      // 일정 응답 DTO
│   ├── UserRequestDto.java           // 회원가입 요청 DTO
│   └── UserLoginDto.java             // 로그인 및 삭제 요청 DTO
├── entity/
│   └── BaseEntity.java               // 생성일/수정일 자동 처리용 상위 Entity
├── repository/
│   ├── ScheduleRepository.java       // 일정 JPA 인터페이스
│   └── UserRepository.java           // 사용자 JPA 인터페이스
├── service/
│   └── ScheduleService.java          // 일정 서비스 로직
└── ScheduleApplication.java          // 메인 애플리케이션
```

---

## 📘 API 명세서

| 기능 | 메서드 | 엔드포인트 | 설명 | 요청 예시 | 요청 Body | 응답 예시 |
|------|--------|------------|------|-----------|------------|------------|
| 회원가입 | POST | `/users/signup` | 사용자 등록 | - | `{ "username": "홍길동", "email": "hong@example.com", "password": "1234" }` | `201 Created` |
| 로그인 | POST | `/users/login` | 사용자 인증 | - | `{ "email": "hong@example.com", "password": "1234" }` | `"Login successful"` or `401 Unauthorized` |
| 사용자 삭제 | DELETE | `/users` | 사용자 인증 후 삭제 | - | `{ "email": "hong@example.com", "password": "1234" }` | `200 OK` or `401 Unauthorized` |
| 일정 생성 | POST | `/schedules` | 일정 등록 | - | `{ "title": "스터디", "contents": "JPA 복습", "userId": 1 }` | 일정 정보 반환 |
| 일정 전체 조회 | GET | `/schedules` | 모든 일정 목록 | - | - | 일정 목록 배열 |
| 일정 단건 조회 | GET | `/schedules/{id}` | 특정 일정 보기 | `/schedules/1` | - | 일정 상세 정보 |
| 일정 수정 | PUT | `/schedules/{id}` | 일정 제목/내용 수정 | `/schedules/1` | `{ "title": "수정", "contents": "내용 수정" }` | 수정된 일정 반환 |
| 일정 삭제 | DELETE | `/schedules/{id}` | 특정 일정 삭제 | `/schedules/1` | - | `200 OK` |

---

## 🗄 ERD (Entity Relationship Diagram)

```
┌─────────────┐      ┌─────────────────────┐
│   users     │      │      schedule       │
├─────────────┤      ├─────────────────────┤
│ id (PK)     │◄─────│ user_id (FK)        │
│ username    │      │ id (PK)             │
│ email       │      │ title               │
│ password    │      │ contents            │
│ (Auditing)  │      │ created_at          │
│             │      │ modified_at         │
└─────────────┘      └─────────────────────┘
```

---

## ✅ 주요 특징 및 구현 내용 요약

- `@ManyToOne`, `@OneToMany`를 활용한 유저-일정 간 연관관계 구현
- 삭제 시 `CascadeType.ALL`, `orphanRemoval = true` 적용으로 유저 삭제 시 일정도 함께 삭제됨
- DTO를 통한 요청/응답 데이터 분리
- `@CreatedDate`, `@LastModifiedDate`를 통한 일정 작성/수정일 자동 처리
- 로그인 및 삭제 기능 시 이메일/비밀번호 일치 여부 확인 및 예외처리(401)
- 코드 가독성과 유지보수를 고려한 레이어별 클래스 분리
