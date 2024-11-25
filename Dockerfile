# 첫 번째 스테이지: 빌드 스테이지
FROM gradle:jdk21-jammy as builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . .

# Gradle 래퍼에 실행 권한 부여
RUN chmod +x ./gradlew

# 종속성 설치
RUN ./gradlew dependencies --no-daemon

# 애플리케이션 빌드
RUN ./gradlew build --no-daemon -x test

# 두 번째 스테이지: 실행 스테이지
FROM ghcr.io/graalvm/jdk-community:21

# 작업 디렉토리 설정
WORKDIR /app

# 첫 번째 스테이지에서 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 사용자 생성 및 권한 설정 (선택 사항)
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# 실행할 JAR 파일 지정
ENTRYPOINT ["java", "-jar", "app.jar"]