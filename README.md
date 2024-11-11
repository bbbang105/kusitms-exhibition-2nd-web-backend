# KUSITMS 제 2회 전시회 웹사이트 Backend 🚀

## 🌐 System Architecture

<img width="727" alt="image" src="https://github.com/user-attachments/assets/53cc8cd7-c4bc-4aab-9831-9aa778cb1836">

## 🧱 ERD

<img width="1210" alt="전시_ERD" src="https://github.com/user-attachments/assets/31ea4828-e296-431f-b599-0c83cdb6b1a3">


## 📄 API Documentation

## 🔒 Rules

### Branch

- 생성한 이슈에 따라서 브랜치 생성 `Ex) feature/#4/login`
- `main branch` : 개발 최종 완료 시 merge
- `develop branch` : 실 개발 진행
- `feature branch` : 각 새로운 기능
- `hotfix branch` : 배포 이후 긴급 수정

### **Commit Message**

- 이슈 번호 붙여서 커밋 `Ex) #4 [feat] : 로그인 기능을 추가한다`
- Body는 추가 설명 필요하면 사용

| ***작업태그*** | ***내용*** |
| --- | --- |
| **feat** | 새로운 기능 추가 / 일부 코드 추가 / 일부 코드 수정 (리팩토링과 구분) / 디자인 요소 수정 |
| **fix** | 버그 수정 |
| **refactor** | 코드 리팩토링 |
| **style** | 코드 의미에 영향을 주지 않는 변경사항 (코드 포맷팅, 오타 수정, 변수명 변경, 에셋 추가) |
| **chore** | 빌드 부분 혹은 패키지 매니저 수정 사항 / 파일 이름 변경 및 위치 변경 / 파일 삭제 |
| **docs** | 문서 추가 및 수정 |
| **rename** | 패키지 혹은 폴더명, 클래스명 수정 (단독으로 시행하였을 시) |
| **remove** | 패키지 혹은 폴더, 클래스를 삭제하였을 때 (단독으로 시행하였을 시) |

### Naming

- **패키지명** : 한 단어 소문자 사용 `Ex) service`
- **클래스명** : 파스칼 케이스 사용 `Ex) JwtUtil`
- **메서드명** : 카멜 케이스 사용, 동사로 시작  `Ex) getUserScraps`
- **변수명** : 카멜 케이스 사용 `Ex) jwtToken`
- **상수명** : 대문자 사용 `Ex) EXPIRATION_TIME`
- **컬럼명** : 스네이크 케이스 사용 `Ex) user_id`

### Package

- global
- user
    - application
    - domain
        - entity
            - entity
            - enums
        - repository
            - ~repository
            - custom
                - ~customRepository
                - ~impl
    - dto
        - request
        - response
    - presentation
    - exception
        - ~Exception
        - ~ErrorResult
        
        …
        
- board

### API Response

```json
{
    "isSuccess": true,
    "code": "200",
    "message": "가이드 챗봇 답변을 가져오는 데 성공했습니다.",
    "payload": {
        "answers": [
            "야구 경기의 시간은 평균 3시간입니다.",
            "경기 시작 시간은 평일에는 오후 6시 30분, 주말이나 공휴일에는 상황에 따라 오후 2시와 오후 5시 시작으로 나뉘어져요!",
            "야구에는 쉬는 시간과 같이 느껴지는 ‘클리닝 타임’이 있어요! 5번의 공격과 5번의 수비가 끝난 ‘5회 말’에 짧은 휴식 시간을 갖습니다.",
            "선수들도 몸을 풀고, 경기장도 재정비 시간을 가집니다! 이 시간을 활용하여 먹거리를 사오거나, 화장실을 다녀오는 것을 추천 드려요!"
        ],
        "imgUrl": null
    }
}
```

- `isSuccess` : 성공 여부
- `code` : 성공 코드, HTTP 상태 코드와 동일함
- `message` : 성공 메세지, 커스텀 가능
- `payload` : 데이터가 들어가는 곳
