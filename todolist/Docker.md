## Docker 정보
- docker-compose로 로컬 개발 환경 구성
- MySQL 8.0.33 버전 이미지 사용

## 유의사항
- DB가 23306 포트 사용하도록 설정함 (다른 프로세스가 해당 포트 사용안하도록 주의)
- OSIV 끄는 옵션 설정함

## 환경 구성 방법
### CLI
```markdown
# 프로젝트 루트에서

# 환경 올리기 (데몬으로)
docker-compose up -d

# 환경 내리기
docker-compose down
```

### Intellij
1. `docker-compose.yml` 파일 상단에 존재하는 화살표 클릭시 환경 올리기
2. `DashBord` 탭에서 Down 버튼 클릭시 환경내리기

