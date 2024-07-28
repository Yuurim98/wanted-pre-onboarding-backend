# wanted onboarding

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
* findAll(Sort sort) 메서드를 이용해 openingId PK값을 기준으로 내림차순 정렬하여 조회 (최근 등록 공고가 먼저 보이도록)
* stream().map().collect 사용

### 5. 채용공고 상세 페이지
* 등록된 채용공고가 아닌 경우 예외처리
  * 검증 실패 시 RuntimeException 예외 발생