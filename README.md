# SLAM Project

## 데이터베이스 설정

### 1. 데이터베이스 설정 파일 생성

프로젝트를 실행하기 전에 데이터베이스 설정 파일을 생성해야 합니다.

1. `src/main/resources/database.properties.example` 파일을 복사
2. 파일명을 `database.properties`로 변경
3. 실제 데이터베이스 정보로 수정:

```properties
db.driver=oracle.jdbc.driver.OracleDriver
db.url=jdbc:oracle:thin:@localhost:1521:XE
db.username=your_username
db.password=your_password
```

### 2. 보안 주의사항

- `database.properties` 파일은 `.gitignore`에 포함되어 Git에 올라가지 않습니다.
- 실제 데이터베이스 접속 정보는 절대 Git에 커밋하지 마세요.
- 팀원들과 공유할 때는 `database.properties.example` 파일만 공유하세요.

### 3. 프로젝트 실행

1. Maven 의존성 설치: `mvn clean install`
2. 프로젝트 빌드: `mvn package`
3. 웹 애플리케이션 실행

## 기술 스택

- Spring Framework
- MyBatis
- Oracle Database
- Maven

