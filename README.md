# 키친포스

## 요구 사항

#### 메뉴 그룹
- 메뉴 그룹을 등록할 수 있다.
    * 메뉴 그룹은 고유 아이디 아이디로 구성되어 있다.
- 메뉴 그룹 리스트를 조회할 수 있다.
    * 등록된 전체 메뉴를 조회할 수 있다.

#### 상품
- 상품을 등록할 수 있다.
- 등록된 상품들의 리스트를 가져올 수 있다.
    - 등록된 전체 상품을 가져온다.
        
#### 메뉴
- 메뉴를 등록할 수 있다.
    - 메뉴는 하나 이상의 메뉴 상품을 갖는다
    - 메뉴 가격은 메뉴 상품의 합이다
    - 메뉴는 메뉴 그룹에 속해 있다. 
- 메뉴 리스트를 조회 한다.

#### 주문
- 주문을 등록할 수 있다.
- 테이블 아이디의 status를 확인한다.
- 하나 이상의 주문 아이템을 가진다.
- 주문 아이템은 메뉴와 수량을 갖는다.
- 주문 아이템의 갯수와 메뉴의 갯수가 다르면 안된다.
- 주문 상태를 변경할 수 있다.
- 주문 상태는 COOKING(조리) MEAL(식사), COMPLETION(계산 완료) 상태를 가질 수 있다.
- 주문 상태가 COMPLETION(계산 완료)이면 주문 상태를 변경할 수 없다.
- 최초 주문 등록 시 상태는 COOKING(조리) 이다.
- 주문 시 주문 테이블에 주문 데이터를 저장한다.
- 주문 시 주문 아이템 리스트를 주문 아이템 테이블에 저장한다.

#### 테이블
- 테이블 리스트를 조회할 수 있다.
- 테이블에 손님이 왓거나 손님이 나갔을때 테이블의 상태를 변경할 수 있다.(empty true | false)
- 테이블의 주문 상태가 조리, 식사이면 테이블 상태를 변경할 수 없다.
- 테이블의 손님 수를 수정할 수 있다.

#### 테이블 그룹
- 서로 다른 테이블을 그룹핑할 수 있다.
- 그룹핑된 테이블을 그룹핑 해제할 수 있다.(통합 계산 완료 시에 처리 가능)
- 그룹핑된 테이블의 그룹핑 해제 시 주문 상태가 조리, 식사이면 해제할 수 없다.

#### Event Storming
* URL: https://app.mural.co/t/kitchenpos2842/m/kitchenpos2842/1625827859898/071be8045e12f01ceaaeccd1f739404baa5fac24?sender=ufe71fd8cb256068d80241893

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |

### 1단계 - 테스트를 통한 코드 보호
#### 요구 사항1
* ``kitchenpos`` 패키지의 코드를 보고 키친포스의 요구 사항을 ``READMO.md``에 작성
* 정리한 키친포스의 요구 사항을 토대로 테스트 코드를 작성한다. 모든 Business Object에 대한 테스트 코드를 작성한다.

#### 프로그래밍 요구 사항 
* Lombok 사용 없이 미션 진행

#### 작업 진행 순서
* [x] 키친포스 요구 사항 분석
* [x] Event Storming 진행해보기
* [x] Business Object 테스트 코드 작성
    * [x] 상품 관련 기능 service test 작성
    * [x] 메뉴 그룹 관련 기능 service test 작성
    * [x] 메뉴 관련 기능 service test 작성
    * [x] 테이블 관련 기능 service test 작성
    * [x] 테이블 그룹 관련 service test 작성
    * [x] 주문 관련 기능 service test 작성

### 1단계 - 테스트를 통한 코드 보호
#### 요구 사항1
* ``kitchenpos`` 패키지의 코드를 보고 키친포스의 요구 사항을 ``READMO.md``에 작성
* 정리한 키친포스의 요구 사항을 토대로 테스트 코드를 작성한다. 모든 Business Object에 대한 테스트 코드를 작성한다.

#### 프로그래밍 요구 사항 
* Lombok 사용 없이 미션 진행

#### 작업 진행 순서
* [x] 키친포스 요구 사항 분석
* [x] Event Storming 진행해보기
* [x] Business Object 테스트 코드 작성
    * [x] 상품 관련 기능 service test 작성
    * [x] 메뉴 그룹 관련 기능 service test 작성
    * [x] 메뉴 관련 기능 service test 작성
    * [x] 테이블 관련 기능 service test 작성
    * [x] 테이블 그룹 관련 service test 작성
    * [x] 주문 관련 기능 service test 작성
    
### 2단계 - 서비스 리팩터링
#### 요구 사항
* 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드에 대해 단위 테스트를 구현한다.
* Spring Data JPA 사용 시 spring.jpa.hibernate.ddl-auto=validate 옵션을 필수로 준다.
* 데이터베이스 스키마 변경 및 마이그레이션이 필요하다면 아래 문서를 적극 활용한다.

#### 프로그램밍 요구 사항
* Lombok은 그 강력한 기능만큼 사용상 주의를 요한다.
    * 무분별한 setter 메서드 사용
    * 객체 간에 상호 참조하는 경우 무한 루프에 빠질 가능성
* 이번 과정에서는 Lombok 없이 미션을 진행해 본다.

* 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    * 기본적으로 Google Java Style Guide을 원칙으로 한다.
    * 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
* indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    * 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
* 3항 연산자를 쓰지 않는다.
* else 예약어를 쓰지 않는다.
    * else 예약어를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    * 힌트: if문에서 값을 반환하는 방식으로 구현하면 else 예약어를 사용하지 않아도 된다.
* 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
    * 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
    * UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
* 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
* 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
* 배열 대신 컬렉션을 사용한다.
* 모든 원시 값과 문자열을 포장한다
* 줄여 쓰지 않는다(축약 금지).
* 일급 컬렉션을 쓴다.
* 모든 엔티티를 작게 유지한다.
* 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

#### 기능 구현 목록
* [x] Spring Data Jpa 사용하도록 소스 수정 
* [x] ProductService의 비즈니스 로직 domain으로 이관 및 단위 테스트 작성
* [x] MenuGroupService의 비즈니스 로직 domain으로 이관 및 단위 테스트 작성
* [x] MenuService의 비즈니스 로직 domain으로 이관 및 단위 테스트 작성
* [ ] TableSerivce의 비즈니스 로직 domain으로 이관 및 단위 테스트 작성
* [ ] TableGroupService의 비즈니스 로직 domain으로 이관 및 단위 테스트 작성
* [ ] OrderService의 비즈니스 로직 domain으로 이관 및 단위 테스트 작성
