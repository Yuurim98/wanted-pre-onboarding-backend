# wanted onboarding

## [API 명세] (https://github.com/Yuurim98/wanted-pre-onboarding-backend/wiki/REST-API)
## 요구사항
1. 채용공고 등록
2. 채용공고 수정
3. 채용공고 삭제
---
### 1. 채용공고 등록
* 등록된 회사가 아닌 경우 예외처리
  * 검증 실패 시 RuntimeException 예외 발생
* 필수 데이터가 없는 경우 예외처리
  * javax.validation.constraints의 @NotEmpty와 @Valid를 사용
  * 검증 실패 시 MethodArgumentNotValidException 예외 발생
  * 예외를 전역에서 처리할 수 있도록 @ControllerAdvice을 사용하여 GlobalExceptionHandler 정의
* 등록이 완료되면 저장된 데이터를 반환

### 2. 채용공고 수정
* 등록된 채용공고가 아닌 경우 예외처리
  * 검증 실패 시 RuntimeException 예외 발생
* JobOpeningUpdateDto를 별도 생성
  * 수정 가능한 필드만 정의
  * companyId, openingId 등 수정이 불가한 필드의 데이터를 함께 요청해도 정의해둔 필드만 수정 진행

### 3. 채용공고 삭제
* 등록된 채용공고가 아닌 경우 예외처리
* 삭제 처리 되지 않은 경우 예외처리
  * 검증 실패 시 RuntimeException 예외 발생

### 4. 채용공고 목록
* Sort 메서드를 이용해 openingId PK값을 기준으로 내림차순 정렬하여 조회 (최근 등록 공고가 먼저 보이도록)
* Pageable 객체를 이용해 페이지 설정
* 페이지 단위로 반환

### 5. 채용공고 상세 페이지
* 등록된 채용공고가 아닌 경우 예외처리
  * 검증 실패 시 RuntimeException 예외 발생
* companyId로 해당 회사의 다른 공고 여부를 확인 
* 존재하는 경우 현재 공고를 제외한 등록된 공고의 openingId를 List로 dto와 함께 반환

### 6. 채용공고 검색 
* 회사 이름(companyName)과 사용기술(skill)로 검색 가능
  * searchType에 따라 검색 로직 상이 (기본값: companyName)
  * searchType이 올바르지 않은 경우 예외처리
    * 검증 실패 시 IllegalArgumentException 예외 발생
  * 페이지 단위로 반환


### 7. 채용공고 지원
* 등록된 채용공고가 아닌 경우 예외처리
* 등록된 회원정보가 아닌 경우 예외처리
* 지원 이력 확인 후 이미 지원한 이력이 있는 경우 예외처리 (한번만 지원 가능)
  * 검증 실패 시 RuntimeException 예외 발생

