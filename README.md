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
| Session / Cookie | Session / Cookie 기반 인증 (Servlet Filter) |

---

## 📁 프로젝트 구조

```
schedule/
├── controller/
│ ├── ScheduleController.java // 일정 CRUD API
│ └── UserController.java // 회원가입 / 로그인 API
├── config/
│ ├── AuthFilter.java // 인증 필터
│ └── FilterConfig.java // 필터 등록 설정
├── domain/
│ ├── Schedule.java // 일정 Entity
│ └── User.java // 유저 Entity
├── dto/
│ ├── CreateScheduleRequestDto.java
│ ├── ScheduleResponseDto.java
│ ├── UserRequestDto.java
│ ├── UserLoginDto.java
│ └── UserResponseDto.java
├── repository/
│ ├── ScheduleRepository.java
│ └── UserRepository.java
├── service/
│ ├── ScheduleService.java
│ └── UserService.java
└── entity/
└── BaseEntity.java // createdAt, modifiedAt 자동 관리
```

---

## 📘 API 명세서

### ✅ 회원가입 / 로그인

| 구분     | 메서드 | URI            | 설명       | Body 예시 |
|----------|--------|----------------|------------|-----------|
| 회원가입 | POST   | `/users/signup` | 유저 생성   | `{ "username": "chris", "email": "chris@example.com", "password": "1234" }` |
| 로그인   | POST   | `/users/login`  | 세션 저장   | `{ "email": "chris@example.com", "password": "1234" }` |

> 로그인 성공 시 서버 세션에 `userId` 저장됨


### ✅ 일정 API

| 구분     | 메서드 | URI              | 설명         | Body 예시 |
|----------|--------|------------------|--------------|-----------|
| 생성     | POST   | `/schedules`     | 일정 등록     | `{ "title": "회의", "contents": "10시 팀 회의", "userId": 1 }` |
| 전체 조회 | GET    | `/schedules`     | 모든 일정 조회 | -         |
| 단일 조회 | GET    | `/schedules/1`   | 특정 일정 조회 | -         |
| 수정     | PUT    | `/schedules/1`   | 일정 수정     | `{ "title": "수정 제목", "contents": "변경된 내용" }` |
| 삭제     | DELETE | `/schedules/1`   | 일정 삭제     | -         |


### ✅ 유저 관리

| 구분     | 메서드 | URI             | 설명             |
|----------|--------|------------------|------------------|
| 전체 조회 | GET    | `/users`         | 유저 전체 조회     |
| 단일 조회 | GET    | `/users/{id}`    | 유저 상세 조회     |
| 수정     | PUT    | `/users/{id}`    | 유저 정보 수정     |
| 삭제     | DELETE | `/users`         | 로그인 상태 유저 삭제<br>비밀번호 확인 필요<br>`{ "email": "xx", "password": "xx" }` |



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

- JPA 기반 Entity 연관관계 및 Auditing 적용
- 세션 기반 인증 구현 (Cookie/Session)
- 유저 삭제 시 관련 일정도 자동 삭제 (`orphanRemoval = true`)
- 구조화된 DTO 패턴과 예외 메시지 처리
