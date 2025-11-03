# java-lotto-precourse

### 📙 각 모듈의 역할

### **Domain**

- **Lotto**: 하나의 로또 번호 컬렉션을 관리
- **LottoNumber**: 개별 로또 번호를 표현하며, 1~45 범위 내의 값인지 검증
- **LottoRank**: 일치 개수와 보너스 번호 여부에 따라 등수를 결정하고, 등수별 상금을 정의
- **WinningLotto**: 당첨 로또 번호와 보너스 번호를 관리하며, 사용자가 구매한 로또와의 일치 여부를 계산

---

### **Service**

- **InputService**: 사용자의 입력값을 받아 검증하고, 이를 도메인 객체로 변환
- **LottoService**: 로또를 생성하고, 구매한 로또의 당첨 결과와 수익률을 계산

---

### **Controller**

- **LottoFactory**: LottoGame 객체를 구성
- **LottoGame**: 프로그램의 전체 실행 흐름을 제어한다.

---

### **View**

- **InputView**: 사용자로부터 구입 금액, 당첨 번호, 보너스 번호를 입력받는다.
- **OutputView**: 발행된 로또 목록과 당첨 통계 및 수익률을 출력한다.

---

### **Generator**

- **NumberGenerator**: 로또 번호 생성의 기본 인터페이스를 정의
- **LottoNumberGenerator**: 1~45 범위의 중복되지 않은 6개의 숫자를 생성하고 오름차순으로 정렬

---

### **Util**

- **InputValidator**: 입력값에 대해 유효성 검증을 수행

---

### **Message**

- **ErrorMessage**: 모든 예외 메시지를 Enum 형태로 관리
- **IOMessage**: 입출력 관련 고정 문구를 Enum 형태로 관

### 📗 실행 흐름

- **사용자 입력 단계**
    - 구입 금액 입력 → 유효성 검증 후 발행 가능한 로또 수량 계산
    - 당첨 번호 입력 → 콤마로 구분된 문자열을 정수 리스트로 변환
    - 보너스 번호 입력 → 단일 정수 검증 및 범위 확인
- **로또 발행 단계**
    - 구입 금액에 따라 로또 개수가 정해짐
    - 로또 개수에 맞춰 로또 발행
    - 번호는 오름차순 정렬 후 출력
- **당첨 결과 계산**
    - 사용자 로또 각각을 당첨 번호와 비교하여 일치 개수 및 보너스 번호 여부 판단
    - LottoRank Enum 기준으로 등수 분류
    - 총 상금 합산 후 수익률 계산
- **출력 단계**
    - 구매한 로또 개수와 번호 목록 출력
    - 각 등수별 당첨 개수 출력
    - 최종 수익률 출력

### 📘 입력 검증

| 검증 항목 | 내용 |
| --- | --- |
| **숫자 여부** | 정규식 \\d+ 로 확인 |
| **빈값 검사** | null 또는 blank 값 확인 |
| **정수 범위 초과 검사** | Integer.MAX_VALUE 초과 시 예외 발생 |
| **양수 여부** | 0 이하일 경우 예외 발생 |
| **1,000원 단위 여부** | 구입 금액은 반드시 1,000원 단위 |
| **로또 번호 개수** | 6개여야 함 |
| **번호 중복 검사** | 중복 번호 존재 시 예외 발생 |
| **범위 검사** | 각 번호는 1~45 사이여야 함 |
| **보너스 번호 중복 금지** | 당첨 번호와 보너스 번호는 중복 불가 |

```java
lotto
 ├── controller
 │   ├── LottoFactory.java
 │   └── LottoGame.java
 ├── domain
 │   ├── Lotto.java
 │   ├── LottoNumber.java
 │   ├── LottoRank.java
 │   └── WinningLotto.java
 ├── generator
 │   ├── LottoNumberGenerator.java
 │   └── NumberGenerator.java
 ├── message
 │   ├── ErrorMessage.java
 │   └── IOMessage.java
 ├── service
 │   ├── InputService.java
 │   └── LottoService.java
 ├── util
 │   └── InputValidator.java
 └── view
     ├── InputView.java
     └── OutputView.java
```
