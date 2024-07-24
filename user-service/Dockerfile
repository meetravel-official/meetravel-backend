FROM amazoncorretto:17.0.11-alpine
# jar 파일이 복사되는 위치
ENV APP_HOME=/usr/app/
# 작업 시작 위치
WORKDIR $APP_HOME
# jar 파일 복사
COPY build/libs/*.jar user-service.jar
# application port
EXPOSE 9001
# 실행
CMD ["java", "-jar", "user-service.jar"]
