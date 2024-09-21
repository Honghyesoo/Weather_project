# 날씨 일기 프로젝트
## 💡 프로젝트 소개
2021년 구글 올해를 빛낸 일상생활 앱 글로벌 다운로드 100만회 이상인 '하루 기록 앱 하루콩'
을 보고 하루의 날씨와 일기를 기록하는 앱 서버 구현.

## 💡 기술 스택
<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=Spring%20Boot&logoColor=black"/><img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white"/><img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black"/><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/><img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white"/><img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white">

## 날씨 데이터 
OpenWeatherMapAPI를 사용하여 세계 날씨 API 호출
- 현재 날씨 데이터
- 미래 날씨 예측 데이터
- 과거의 날씨 데이터를 제공

## 💡 주요 기능
1. 날씨 일기 저장 API 구현
   - OpenWeatherMap 에서 데이터 받아오기
   - 받아온 데이터 (json) 사용 가능하게 파싱
   - db 저장
  
2. 날씨 데이터 CRUD
   - 날씨 일기 조회 API 구현
   - 날씨 일기 수정 API 구현
   - 날씨 일기 삭제 API 구현
  
3. 트랜잭션 사용
   - 날씨 데이터 저장 시: OpenWeatherMap API에서 데이터를 받아와 DB에 저장할 때, API 호출이 성공하고 DB 
     에 데이터가 정상적으로 저장되었을 때만 트랜잭션을 완료하고, 오류가 발생하면 롤백.
   - 날씨 데이터 수정 시: 수정 작업 중 문제가 생기면 롤백하여 원본 데이터를 보존.
   - 날씨 데이터 삭제 시: 삭제 작업에서 문제가 발생하면 트랜잭션을 롤백하여 데이터의 삭제를 방지.
  
4. 날씨 저장 스케줄링
    - 매일 자정에 OpenWeatherMap API를 호출하여 다음 날의 날씨 데이터를 미리 가져와 DB에 저장하는 기능을 
    구현
    -> 이를 통해 날씨 데이터를 캐싱하여 API 호출 빈도를 줄이고, 사용자가 빠르게 날씨 정보를 조회할 수 있도록 최적화함.
    - 캐싱 적용: 매일 날씨 데이터를 저장함으로써 DB에서 바로 읽어오는 방식으로 성능을 개선하며, 필요 시 캐싱된 데이터를 주기적으로 갱신.
5. Spring Boot Logging
   - 로깅 활용: 서비스의 동작 상태와 오류 발생 여부를 모니터링하기 위해 Spring Boot의 로깅 기능을 사용함
   - 로그를 통해 서버의 상태를 파악하고, 예외 상황 발생 시 원인을 분석.
6. API Document - Swagger 활용
   - Swagger UI를 통해 개발자와 사용자 모두가 API 명세를 쉽게 확인할 수 있으며, 테스트 환경을 제공하여 API 
    를 직관적으로 활용할 수 있도록 지원.


