- 초보자를 위한 도커 안내서 : https://subicura.com/2017/01/19/docker-guide-for-beginners-1.html

- Docker Swarm을 이용한 쉽고 빠른 분산 서버 관리 : https://subicura.com/2017/02/25/container-orchestration-with-docker-swarm.html

*Docker Swarm => 쿠버네티스와 같은 레벨

- App을 Container로 말아야되는 이유
서버 환경과 어플리케이션의 의존성을 제거해줌
컨테이너 기술로 OS, bin, lib 까지 말아서 배포할 수 있음
설치법을 몰라도 표준화된 방법으로 배포가 가능하다.

- docker image
컨테이너를 만들기 위한 설정이 모아져 있음
- docker container
실행 가능한 단위
- container orchestration (kubernetes)\
분산되어 있는 대규모 컨테이너를 자동으로 관리
  - 스케쥴링 : 다수의 컨테이너를 다수의 호스트(클러스터)에 적절하게 분산 실행
  - 상태 관리 : 원하는 상태(desired state)로 실행상태를 유지 (오토 페일오버)
  - 배포 / 스케일링 : 서비스 중단 없이 자동화된 배포를 통해 업데이트를 하거나 스케일을 확장/축소
  - 서비스 디스커버리 : 새로 뜨거나 옮겨간 컨테이너에 접근할 수 있는 방법을 제공
  - Resource 연결 관리 : 컨테이너의 라이프 사이클에 맞춰 관련 자원을 연결 (메타 데이터/네트워크/스토리지)
