
<h2 align="middle">4week_spring</h2>
<p align="middle">
<img src="https://github.com/Hun425/4week_spring/assets/147483675/c0d4f136-a8a8-4f75-b7a1-01a1de5ee139" alt="No Image">


</p>
<p align="middle">스프링 4주간 기능 구현 마스터하기!</p>

<br>

# 🔥 1주차 요구사항! `06/03~06/09`

## 게시판 CRUD + 로그인/로그아웃

### 기능 명세
<br>

- 게시글 작성
    - 제목
    - 내용
- 게시글 수정
    - 제목
    - 내용
- 게시글 삭제
- 게시글 조회
    - 제목, 작성자, 내용, 조회 수, 추천 수, 비 추천 수, 댓글 수, 작성 시간
    - 댓글 목록
        - 작성자, 내용, 작성 시간, 추천 수, 비 추천 수
        - 대댓글
- 게시글 목록 조회
    - 제목, 작성자, 조회 수, 추천 수, 작성 시간
    
- 게시글 추천 / 비추천
- 댓글 추천 / 비추천

- 로그인
    - ID, PW, PW 확인
    - PW 저장 시 암호화 필수
- 로그아웃

※ 게시글 목록 조회, 게시글 조회 기능을 제외한 모든 기능은 로그인 한 유저만 가능
※ 게시글 수정 및 삭제 작업은 해당 게시글을 작성한 유저만 가능
<br>

---

<br>

### 다이어 그램

<br>

![개념설계도 drawio](https://github.com/Hun425/4week_spring/assets/147483675/c294d9ee-96aa-47bf-8bad-f5f16716ae58)

![1week_spring](https://github.com/Hun425/4week_spring/assets/147483675/29826ca9-f7f3-477e-b5a2-4dfde4382365)



<br>

### ⚙ 1주차 구현 결과!

- 구현 성공 : 게시판 CRUD, 댓글 CRD, 게시글 추천, 비추천, 유저 로그인, 회원가입, 로그아웃
- 구현 실패 : 대댓글, 조회수

<br>



<br>

### ⛏ 1주차 삽질내역 정리
- https://velog.io/@chae0738/4weekspring-1주차-트러블-슈팅

<br>
<br>

# 🔥 2주차 요구사항!  `06/10~06/16`

## 게시판 CRUD + 로그인/로그아웃

### 기능 명세

1. 세션 인증 → 토큰 인증(JWT) 변경 및 Refresh Token 까지 적용 + Spring Security
2. ResponseEntity 이용해서 응답 데이터 및 Http Status Code 까지 적절한 응답 할 수 있도록 변경
3. @ExceptionHandler 사용해서 전역 예외처리 하기 + Enum 사용해서 예외 발생 시 던져줄 객체 만들기
4. 입력값 검증 Validation 적용하기

---

<br><br>

### ⚙ 2주차 구현 결과!

- 구현 성공 : 토큰 인증(JWT), ResponseEntity 응답, 예외 전역 처리
- 구현 진행중 : Validation 적용

### ⛏ 2주차 삽질내역 `트러블 슈팅`

- https://velog.io/@chae0738/4weekspring-2주차-트러블-슈팅-smvubds2
<br>
<br>


# 🔥 3주차 요구사항!  `06/17~06/23`

## 게시판 CRUD + 로그인/로그아웃

### 기능 명세

1. 1,2주차에 기능 구현 못한 부분 완성하기
2. OAuth2 로그인 및 소셜 로그인 연동 (원하는 소셜 로그인 기능 하나 + OAuth2를 사용한 사용자 인증 구현)
3. 파일 업로드 및 다운로드 기능 구현 (Spring Boot의 MultipartFile 사용)

---

<br><br>

### ⚙ 3주차 구현 결과!

- 구현 성공 :
- 구현 진행중 :

### ⛏ 3주차 삽질내역 `트러블 슈팅`

- 
