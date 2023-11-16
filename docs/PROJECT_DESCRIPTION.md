## ⚽ 실행 화면

### 시작화면

![StartPage.PNG](..%2F..%2F..%2F4%EC%A3%BC%EC%B0%A8%EC%82%AC%EC%A7%84%EB%93%A4%2FStartPage.PNG)
***
　   
　   
### 실행예시

![Example1.PNG](..%2F..%2F..%2F4%EC%A3%BC%EC%B0%A8%EC%82%AC%EC%A7%84%EB%93%A4%2FExample1.PNG)　   
***
![Example2.PNG](..%2F..%2F..%2F4%EC%A3%BC%EC%B0%A8%EC%82%AC%EC%A7%84%EB%93%A4%2FExample2.PNG)　   
***
![Example3.PNG](..%2F..%2F..%2F4%EC%A3%BC%EC%B0%A8%EC%82%AC%EC%A7%84%EB%93%A4%2FExample3.PNG)　   
　   
　   
## ⚾ 패키지 설명

~~~
 └─src
   ├─main
     └─java
         └─christmas
             ├─config       /** IoC Container */
             ├─constants    /** 상수들을 관리 */
             ├─controller   /** MVC의 Controller. 게임 흐름을 관리 */
             ├─domain       /** MVC의 Model */
             │  ├─dto       /** 계층 간 데이터를 전송함. Controller -> View로 DTO 객체 전달 */
             │  └─entity    /** 비지니스 도메인 객체. 저장소에 매핑됨 */
             │      ├─event /** 이벤트와 관련된 Model */
             │      ├─menu  /** 메뉴와 관련된 Model */
             │      └─order /** 주문과 관련된 Model */
             ├─messages     /** 예외, 입출력 메세지들을 관리 */
             ├─repository   /** Entity Model을 저장 */
             ├─service      /** 여러 객체가 묶인 비지니스 로직을 처리 */
             ├─util         /** 재사용 유틸리티 클래스를 모아둠 */
             ├─validation   /** 자주 사용될 수 있는 검증 기능을 모아둠 */
             └─view         /** MVC의 View. 사용자에게 보여지는 UI를 담당 */
~~~
　   
　   
## 🏀 클래스 설명

### 🚣 AppConfig Class

`AppConfig` 클래스는 `Config` 인터페이스를 구현하며, 어플리케이션의 다양한 객체들을 생성하고 관리합니다.　   
이들은 IoC 컨테이너와 의존성 주입 패턴을 활용하여 어플리케이션의 유연성과 확장성을 높이고, 코드 관리 및 테스트를 용이하게 합니다.　   
이와 관련하여 아래 블로그에 작성해두었습니다.　   
[객체에서 의존하는 객체가 너무 많을 때는 DIP(의존관계 역전 원칙), DI(의존성 주입), IoC Container을 적용하자](https://blog.naver.com/inpink_/223254913501)　   
[싱글톤 패턴에 IoC 컨테이너와 LazyHolder를 적용하여 싱글톤의 단점을 최소화](https://blog.naver.com/inpink_/223260522877)　   
　   
- **싱글톤 패턴**:
    - `AppConfig`는 싱글톤 패턴을 사용하여 구현됩니다.
    - `getInstance()` 메소드를 통해 전역적으로 하나의 인스턴스만을 유지합니다.
    - `LazyHolder` 내부 클래스를 사용하여 thread-safe한 인스턴스 생성을 보장합니다.

- **의존성 생성 및 제공**:
    - 여러 컴포넌트(예: `OrderController`, `OrderService`, `BenefitsService`)의 인스턴스를 생성합니다.
    - 필요한 의존성을 각 컴포넌트의 생성자에 주입합니다.

- **IoC 컨테이너 역할**:
    - 애플리케이션의 흐름 제어를 개발자가 아닌 `AppConfig`가 담당합니다.
    - 코드의 결합도를 낮추고 유연성 및 확장성을 높입니다.

### 🗾 Config Interface

`Config` 인터페이스는 `AppConfig`가 구현해야 할 메소드들을 정의합니다.

- **컴포넌트 접근 방법 정의**:
    - 애플리케이션에서 각 컴포넌트에 접근하기 위한 메소드들을 정의합니다.

- **유지보수성 향상**:
    - `Config` 인터페이스를 사용함으로써 구현체의 변경과 확장이 용이합니다.



### 🏔️ IntegerConstants Enum

어플리케이션에서 사용되는 다양한 정수형 상수들을 enum 클래스에 모아 관리합니다.

### 🏕️ DtoMapper Class

`DtoMapper` 클래스는 Entity를 Data Transfer Object(DTO)로 변환하는 정적 메소드를 제공합니다.　   
추후 프로젝트 규모가 커진다면 각 Entity별로 개별 Mapper를 두는 방식으로 책임을 분리하는 것이 유리합니다.　   

### 🏖️ Badge Enum

`Badge`는 이벤트 중에 부여될 수 있는 다양한 배지를 정의합니다.　   
내부 비지니스 로직인 `getBadgeByPrice` 메서드는 매개변수로 주어진 `Money` 객체의 가격에 따라 적절한 배지를 반환합니다.　   

- enum을 통해 잘못된 문자열 또는 숫자를 사용하여 오류가 발생하는 것을 방지합니다.
- 'NO', 'STAR', 'TREE', 'SANTA'와 같이 의미 있는 이름을 사용하여 코드의 가독성을 높입니다.
- 배지에 대한 정보가 enum에 중앙 집중화되어 있어, 새로운 배지가 추가되거나 기존 배지의 정보가 변경되어야 할 경우에도 enum 클래스 내에서만 수정하면 됩니다.
- Enum은 싱글톤이고 임의로 객체를 생성할 수 없습니다. 메모리 사용에 효율적입니다.

### 🏜️ Benefit Class

`Benefit` 클래스는 이벤트 하나 당 부여되는 혜택을 나타내며, "할인 금액"과 "증정품들"을 관리합니다.　   

### 🏝️ Benefits Class

`Benefits` 클래스는 모든 이벤트의 혜택을 담습니다. 각 Event에 따라 부여된 혜택 Benefit를 `EnumMap`을 사용하여 저장합니다.　   


### 🏟️ ChristmasDayDiscountEvent Enum

크리스마스 이벤트 기간 동안의 "할인 조건"을 정의하고 "계산"하는 기능을 제공합니다.　   
`Inner Enum`을 통해, 하나의 이벤트에 대해 "할인 조건"과 "할인 금액"을 한 곳에서 관리합니다.　   
크리스마스 이벤트의 "할인 조건"과 "할인 금액"은 한 곳에서만 사용되고, 둘 다 정해진 값으로 열거되기에 Inner Enum 구조를 선택했습니다.　   

### 🏛️ Event Enum

모든 이벤트들의 종류를 열거하고, 각 이벤트 클래스에서 Benefit을 계산하도록 메서드를 참조합니다.　   
모든 이벤트들에서 Benefit을 계산하여 Benefits 객체로 반환합니다.　   

### 🏨 Gifts Class

모든 증정품들을 List<Item> 형태로 저장합니다.

### 🗼 Starred Enum

별이 달린 날짜들과, 그렇지 않은 날짜들을 YES / NO 2가지 타입으로 구분하여 관리합니다.　   
날짜를 매개변수로 받아 어떤 타입에 해당되는지 판단합니다.　   

### 🌃 Item Interface

메뉴판의 각 아이템은 Item 인터페이스를 구현합니다.　   
이는, 서로 다른 메뉴에 있는 아이템들끼리의 공통 기능을 추상화합니다.　   

### 🚉 Menu Enum

`Menu` enum 클래스는 메뉴의 "카테고리"와 함께 각 카테고리에 속하는 "아이템들"을 정의합니다.　   
각 카테고리의 아이템들은 Item의 배열 형태입니다.　   

### 🚈 Appetizer Enum

각 카테고리의 아이템들을 열거합니다.
각 카테고리별 아이템들의 종류가 고정되어 있기 때문에 열거형 Enum을 사용했습니다.　   
만약 사용자에 의해 매번 새롭게 Item이 추가된다면 abstract class를 사용하는 것이 유리하다고 생각합니다.　   

### 🚇 Order Class

하나의 전체 주문에 관련된 모든 데이터를 담습니다.　   
Order은 `IndexModel abstract class`을 상속받아, 모든 Order 객체가 고유 id를 가질 수 있습니다.　   
이는 여러 개의 Order들을 구분하는 데 사용합니다.　   
저장소에 Order 객체를 저장할 때 고유 Id를 `primary Key`로 사용합니다.　   

### 🚍 DateOfVisit class

사용자가 입력한 "날짜"를 "정적 팩토리 메서드"를 통해 완전한 날짜인 `LocalDate`로 변환하여 반환합니다.　   
날짜에 관련된 검증 로직을 내부에 가지고 있습니다.　   

### 🌇 Money Class

전체 프로젝트에서 사용되는 돈은 모두 Money 객체를 사용합니다.　   
원시값 int를 사용하지 않고 VO화하여, 추후 Money와 관련된 비지니스 로직이 변경되더라도 Money 객체 내부에서만 수정하면 됩니다.　   
또, 외부 객체에서 Money에 대한 정보를 감춰 캡슐화했습니다.　   

### 🚎 MemoryRepository

순차적인 index를 가지는 방식으로 저장되는 저장소를 위해 필수적인 기능을 구현해놓은 추상 클래스입니다.

### 🛴 MemoryOrderRepository

"배지는 2024 새해 이벤트에서 활용할 예정입니다." 라는 요구사항을 만족시키기 위해 Order 객체가 저장소에 저장될 필요가 있다고 판단했습니다.　   
여러 Order 객체가 저장된다면, Order 객체들끼리 서로 구분해야만 추후에 특정 Order 객체에 담긴 배지 데이터를 받아올 수 있습니다.　   
Order 객체들을 구분하는 것을 고유 id값을 이용하여 구현했습니다.　   
Map에 {id : Order 객체} 형식으로 저장하고, id를 통해 Order 객체를 조회할 수 있습니다.　   

