# meetravel-backend

![Spring](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Kubernetes](https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white)

## 디렉토리 구조

### 디렉토리 설명

## 백엔드 구동 방법

1. 최상위 폴더에 있는 `docker.sh`를 실행한다.
   - Prerequirements: Docker
2. 다음의 순서로 실행시킨다.
   1. config
   2. discovery
   3. apigateway
   4. *-service

## Contribution

⚠️ **정확한 방법으로 커밋을 진행해야 푸시한 내용이 최상위 폴더에 다 풀어지는 사고를 방지할 수 있다‼️‼️**

1. 최상위 폴더인 `meetravel-backend` 폴더 안에 원하는 서비스(*-service) 폴더를 만들고 그 안에서 개발을 진행한다.
2. 자동으로 commit을 하도록 만들지 말고 일단 최상위 폴더로 이동한다.
3. 최상위 폴더에서 커밋을 하고 푸시한다. 브랜치도 잘 확인한다.
   - 만약 이미 내용이 커밋되어 있다면 unstage 한 뒤 다시 진행한다.
4. PR을 하고 리뷰를 진행한다.

## 서비스 코드마다 적용할 것

### 적용해야 하는 것 (Required)

> **Service Discovery** - Eureka Server

[Discovery - README.md](https://github.com/meetravel-official/meetravel-backend/blob/main/discovery/README.md#서비스-등록을-위한-설정)

> **Distributed Tracing** - Spring Cloud Sleuth + Zipkin

### 직접 추가해줘야 할 설정

`build.gradle`
```groovy
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-sleuth'
    implementation 'org.springframework.cloud:spring-cloud-sleuth-zipkin'
    implementation 'org.springframework.cloud:spring-cloud-starter-zipkin' // zipkin 3.0 부터 sleuth-zipkin 으로 변경
}
```

### 관련 설정

`meetravel-official/config/application.yml`
```yaml
# Zipkin 서버 정보
spring:
  zipkin:
    base-url: http://localhost:8085
```

### 필요할 때 적용하면 좋은 것 (Preferred)

> **Circuit Breaking** - Resilience4j

`build.gradle`
```groovy
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j'
    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
}
```

(구글링 자료 추가하기)

## 참조 화면

(필요한 사진들 추가하기)
- 시스템 구조도
- ERD
- 실행 화면

등등
