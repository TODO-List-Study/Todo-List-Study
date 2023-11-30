# Todo-List-Kotlin Study

## Reference
> 극락코딩

<hr>

### Based Dependency

- Java or Kotlin 원하는 언어를 선택해주세요.
- SpringBoot도 원하는 버전을 선택해주세요.

<hr>

### 필수 사항

- TODOLIST CRUD API를 구현(생성, 수정, 삭제, 단건조회, 전체조회)
- RESTFUL한 API를 구현하는 것을 목적으로
- 클린코드란?
- 객체지향 패러다임을 고민하며 구현을 진행
- README를 꼭 작성해주세요!!(도메인 구성, 요구사항 분석을 통한 기능 목록 도출)
- 본인이 생각하기에 TODO-LIST에 필요하다고 생각되는 기능이 있다면 추가해도 좋아요!
- Junit, Mockito를 이용한 테스트 코드 작성

<hr>

### 주차별 진행

**0주차**
- 진행방향에 대한 토론 및 사용 tool & Language 토론

**1주차**
- (assignment) in-memory 기반 todo-list 구현
- (assignment) 봉투 패턴 적용 및 에러 전역처리
- (cs) spring mvc의 동작원리 파악  

**2주차**
- (assignment) db 기반 todo-list 구현
- (assignment) bulk-create api 만들기 (max: 5000개, count를 입력받고, 다른 todo 컬럼에는 랜덤한 값을 입력)
- (cs) mysql index, memory vs disk
  - index를 설명해보자! 그런데 memory랑 disk의 차이는 얼마나 날까?

**3주차**
- (assignment) viewer를 count해주는 api를 만들어주세요. api 호출 진행시, viewer를 1만큼 올려주도록요. (DB에 count 데이터 필요)
- (assignment) api-doc 및 테스트 커버러지 (swagger, rest docs, jacoco)
- (assignment) Cahcing을 이용하여 조회속도를 향상시켜보자
- (cs) test-code (stub, mock)
  - 통합 테스트, 단위 테스트.. 등등 많은 테스트가 있는데, 이건 뭐고, 테스트는 왜 작성해야할까?
  - Caching을 적용해야하는 이유는 알겠는데, Local Cahce, Redis? 뭐가 더좋고 분산시에는?

**4주차**
- (assignment) restful api (7 promise) 규칙을 지켜 수정해주세요.
- (assignment) 변화에 쉽게 대처하기
- (cs) restful이란, http, tcp, udp
- (cs) 디자인패턴, 내가 생각하는 클린코드
  - 다양한 디자인패턴들.. 언제 어떻게 쓸까?

**5주차**
- (assignment) OOP적인 설계에 초점을 두어보자 (Getter를 지양 등등)
- (assignment) 성능지표를 문서화하여보자
- (assingment) 쿼리튜닝에 대해 배워보자
- (cs) 쿼리튜닝은 무엇이며, OOP에 근접한 설계는 무엇인가?

**6주차**
- (assignment) 테라폼을 통한 배포환경을 구축하여보자
- (assignment) 모니터링 시스템을 구축해보자
- (cs) IaC는 무엇이며, 어떠한 장점이존재하는가?

**7주차**
- (assignment) 부하테스트 (default는 ngrinder)
- (assignment) 통합테스트를 작성하여보자 (testcontainer)
- (cs) 성능 지표 기반의 개선 방법
  - 부하가 발생했다. cpu가 터지네? api-latency느리네?, ram이 부족하네!
 
**8주차**
- (assignment) 멀티모듈 패키지로 분리시켜보자
- (assignment) 서버분산? 할게 있다면 적용해보자!
- (assignmnet) DB 레플리케이션 처리
- (cs) 분산서버에서 캐시 동기화 및 클러스터링
