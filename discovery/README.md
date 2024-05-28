# MeeTravel Discovery Server

동작 가능한 Client Service를 검출하는 서버

## 서버 URL

로컬 기준: http://localhost:8761/

## 서비스 등록을 위한 설정

### 직접 추가해줘야 할 설정

해당 서버에 만든 서비스를 등록하고 싶다면 아래의 설정들을 추가한다.

`build.gradle`
```groovy
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
}
```

`XXXServiceApplication.java`
```java
@EnableDiscoveryClient // discovery client 활성화
@SpringBootApplication
public class XXXServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }
}
```

### 관련 설정

아래는 config 저장소에 있는 관련 정보다.

`meetravel-official/config/application.yml`
```yaml
eureka:
  instance:
    instance-id: ${spring.application.name}:${spring.application.instance_id:${random.value}}
  client:
    register-with-eureka: true # eureka 서버에등록
    fetch-registry: true # 외부검색가능
    service-url:
      defaultZone: http://admin:admin@localhost:8761/eureka
```
