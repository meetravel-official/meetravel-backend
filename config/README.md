# MeeTravel Config Server

서비스들이 필요로 하는 설정 정보들(application.yml 등)을 다루기 위한 spring cloud config server

아래 내용은 요약한 것. 더 자세한 내용은 가장 아래의 Reference 1 & 2 링크 확인할 것.

## 동작 방식

상위 레포지토리 중 `config`에 저장되어 있는 설정 파일을 중개해주는 역할

## 사용 장단점

### 장점

1. 설정 정보를 외부로 분리하면, 설정 정보가 변할 때 설정 서버의 **재배포가 필요없습니다**.
2. 설정 정보를 애플리케이션에서 관리하는 것이 아니라 분리해서 외부 저장소에서 관리함으로서 보안적인 측면을 강화할 수 있습니다.
3. 공통적인 구성을 공유하는 MSA 가 자신의 속성 설정으로 유지/관리하지 않고도 동일한 속성을 공유할 수 있습니다. 또한 공유되는 속성을 한 곳에서 변경함으로서 모든 MSA 에 적용 가능합니다. 즉, **설정 정보의 유연한 적용과 관리**가 가능합니다.

### 단점

1. Config Server 의 장애가 애플리케이션으로 전파될 수 있습니다.
2. 우선 순위에 의해 설정 정보가 의도치 않게 덮어씌워질 수 있습니다.

## 작동 로직

`Application(MSA)` - `Config Server` - `Config Server 가 바라보는 설정 외부 저장소`

## 우선순위

이는 로컬에 존재하는 설정 파일과 설정 파일 저장소에 존재하는 설정 파일의 우선 순위를 결정합니다.
가장 우선순위가 높은 것은 설정 파일 저장소의 {application name}-{profile}.yml 입니다!

1. 설정 파일 저장소의 {application name}-{profile}.yml
2. 설정 파일 저장소의 {application name}.yml
3. 로컬의 {application name}-{profile}.yml
4. 로컬의 {application name}.yml
5. 설정 파일 저장소의 application.yml
6. 로컬의 application.yml

## 설정 정보 업데이트 시 ❗️❗️

actuator refresh를 client마다 호출하게 해서 갱신시킬 수 있지만 모든 인스턴스를 호출할 수는 없는 일이다.

따라서 변경을 감지하면 해당 사항을 자동으로 전파하는 기능을 사용하도록 할 것이다. ex) `rabbitmq` or `kafka` with docker(or k8s)

---
**References**

1. https://velog.io/@choidongkuen/Spring-Cloud-Spring-Cloud-Config-에-대해

2. https://github.com/eGovFramework/egovframe-msa-edu/tree/43f0351bda5818e617d7f092077c8a937e52dcde
