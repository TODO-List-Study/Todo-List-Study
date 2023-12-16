# TO-DO List Study

## Reference

> 극락코딩
> 

---

### Based Dependency

- Java or Kotlin 원하는 언어를 선택해주세요.
- SpringBoot도 원하는 버전을 선택해주세요.

---

### 필수 사항

- TODOLIST CRUD API를 구현(생성, 수정, 삭제, 단건조회, 전체조회)
- RESTFUL한 API를 구현하는 것을 목적으로
- 클린코드란?
- 객체지향 패러다임을 고민하며 구현을 진행
- README를 꼭 작성해주세요!!(도메인 구성, 요구사항 분석을 통한 기능 목록 도출)
- 본인이 생각하기에 TODO-LIST에 필요하다고 생각되는 기능이 있다면 추가해도 좋아요!
- Junit, Mockito를 이용한 테스트 코드 작성

---

### 주차별 진행

**0주차**

- 진행방향에 대한 토론 및 사용 tool & Language 토론

**1주차**

- (assignment) in-memory 기반 todo-list 구현
- (assignment) 봉투 패턴(error, data, message) 적용 및 에러 전역처리
    - error : 에러코드
    - data : Client에게 Response
    - message : 에러 메시지
- (cs) spring mvc의 동작원리 파악

**2주차**

- (assignment) db 기반 todo-list 구현
- (assignment) bulk-create api 만들기 (max: 5000개, count를 입력받고, 다른 todo 컬럼에는 랜덤한 값을 입력)
- (assignment) 필요 컬럼 Index 처리 (unique, not null)
- (cs) mysql index, memory vs disk
    - index를 설명해보자! 그런데 memory랑 disk의 차이는 얼마나 날까?

**3주차**

- (assignment) viewer를 count해주는 api를 만들어주세요. api 호출 진행시, viewer를 1만큼 올려주도록요. (DB에 count 데이터 필요)
- (assignment) api-doc 및 테스트 커버러지 (rest docs) (선택사항)
- (assignment) Cahcing을 이용하여 조회속도를 향상시켜보자
- (cs) test-code (stub, mock)
    - 통합 테스트, 단위 테스트.. 등등 많은 테스트가 있는데, 이건 뭐고, 테스트는 왜 작성해야할까?
    - Caching을 적용해야하는 이유는 알겠는데, Local Cahce, Redis? 뭐가 더좋고 분산시에는?

**4주차**
- (assignment) restful api (7 promise) 규칙을 지켜 수정해주세요.
- (assignment) 변화에 쉽게 대처하기
- (assignment) DB 스케줄링을 통해 조회수가 5이하인 게시물은 삭제하여주세요
- (cs) restful이란, http, tcp, udp
- (cs) 디자인패턴, 내가 생각하는 클린코드
    - 다양한 디자인패턴들.. 언제 어떻게 쓸까?
- (cs) 멱등성

**5주차**

- (assignment) OOP적인 설계에 초점을 두어보자 (Getter를 지양 등등)
    - (assignment) 추상화
- (assignment) 성능지표를 문서화하여보자
- (assingment) 쿼리튜닝에 대해 배워보자 (보류)
- (cs) 쿼리튜닝은 무엇이며, OOP에 근접한 설계는 무엇인가?

**6주차**

- (assignment) 테라폼을 통한 배포환경을 구축하여보자
- (assignment) 모니터링 시스템(배포 서버 모니터링 시스템)을 구축해보자(default grafana)
- (assignment) 디스코드 에러 알림 처리
- (cs) IaC는 무엇이며, 어떠한 장점이존재하는가?

**7주차**

- (assignment) 부하테스트 (default는 ngrinder, locust, jmeter)
- (assignment) 통합테스트를 작성하여보자 (testcontainer)
- (cs) 성능 지표 기반의 개선 방법
    - 부하가 발생했다. cpu가 터지네? api-latency느리네?, ram이 부족하네!

**8주차**

- (assignment) 멀티모듈 패키지로 분리시켜보자
- (assignment) 서버분산? 할게 있다면 적용해보자! (보류)
- (assignmnet) DB 레플리케이션 처리 (공부)
- (cs) 분산서버에서 캐시 동기화 및 클러스터링

## Ground Rule

- 매주 일요일 오후 9시(유동적으로 스케줄따라서 변경 주내에서)
    - 간략하게 코드 흐름 설명(주요한 코드위주로)
    - 스터디전까지 무조건 PR올리고 리뷰남길수있도록 PR은 최소한 일요일 오전 12시까지는
    - 코드리뷰는 궁금한점, 진짜 이해안가는점, (나는 이렇게했는데 님은 어케생각함?)
- 매주 진행전에 [README.md](http://README.md) 한 주차 계획 정리
- 인덱싱, 캐싱처리 전에 지표를 기술블로그쓰듯이 정리해서 wiki 디렉터리안에 적어주세요
- 브랜치 이름 : 주차-github 아이디
- Repository를 fork하여 PR 올려주세요

## Group Member

- @seonghoo1217
- @jjinwo0
- @jhnyuk

## Docs
- [1주차 과제 분석](https://github.com/seonghoo1217/Todo-List-Study/wiki/1%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-%EC%A7%84%ED%96%89%EC%83%81%ED%99%A9)
