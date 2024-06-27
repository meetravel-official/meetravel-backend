# docker run --name meetravel-config -d -p 8888:8888 -e profile=prod meetravel/meetravel-backend-config
FROM amazoncorretto:17.0.11-alpine

# curl(healthcheck)
RUN apk --no-cache add curl
# directory 생성
RUN mkdir -p /usr/app/config
# jar 파일이 복사되는 위치
ENV APP_HOME=/usr/app/
# 작업 시작 위치
WORKDIR $APP_HOME
# jar 파일 복사
COPY build/libs/*.jar config.jar
# application port
EXPOSE 8888
# 실행
CMD ["java", "-Dsearch.location=/usr/app/config", "-jar", "config.jar"]
