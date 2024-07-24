# ELK

meetravel-backend를 위한 ELK 스택

---

## 실행 방법 1

[deviantony/docker-elk: 'default' branch](https://github.com/deviantony/docker-elk/tree/main?tab=readme-ov-file)

[deviantony/docker-elk: 'tls' branch](https://github.com/deviantony/docker-elk/tree/tls?tab=readme-ov-file) ✅

필요하다면 커스텀한 docker-compose.yml 파일을 삽입하겠으나,
처음엔 유명한 다른 레포지토리의 기본 설정들을 따라가고자 한다.

인스턴스를 도커나 K8s로 Spring Cloud 메인 프로젝트와 분리해서 띄워줄 예정이다.

추후 필요하다면 fork한 뒤 커스텀 해주자. (MIT LICENSE 라 가능)

### Reference

[Docker-Compose를 이용하여 ELK Stack 시작하기](https://teichae.tistory.com/entry/Docker-Compose를-이용하여-ELK-Stack-시작하기-1)
- 'default' branch 기준이긴 하지만 'tls'와의 차이점이 그다지 없으므로 참고할 때 유용함.

[docker-compose로 ELK 8 버전 사용해보기 2부(https)](https://xodns.tistory.com/119)
- 'tls' branch 기준으로 진행하는 실행 예시.
- 위의 레퍼런스가 더 자세하긴 하다.

## 실행 방법 2

[elastic/elasticsearch - docker-compose.yml](https://github.com/elastic/elasticsearch/blob/8.13/docs/reference/setup/install/docker/docker-compose.yml)
- .env 파일과 같이 보도록 하자.

ElasticSearch에서 공식 레포지토리로 제공하는 docker-compose.yml 파일이다.

기본적으로 elasticsearch를 3개 띄워서 분리 환경을 제공하긴 한데 그렇게까지 할 필요가 있나 싶어서 우선순위를 낮췄다.

### Reference

[ElasticSearch 공식 홈페이지 - Install Elasticsearch with Docker](https://www.elastic.co/guide/en/elasticsearch/reference/8.13/docker.html#docker)
