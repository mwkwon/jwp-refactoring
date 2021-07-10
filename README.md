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
* [ ] Business Object 테스트 코드 작성
    * [x] 상품 관련 기능 service test 작성
    * [x] 메뉴 그룹 관련 기능 service test 작성
    * [x] 메뉴 관련 기능 service test 작성
    * [ ] 테이블 관련 기능 service test 작성
    * [ ] 테이블 그룹 관련 service test 작성
    * [ ] 주문 관련 기능 service test 작성
