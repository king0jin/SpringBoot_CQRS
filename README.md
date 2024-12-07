# SpringBoot_write_CQRS

## SpringBoot 데이터 기록 프로젝트
### 1. SpringBoot 프로젝트 생성 
+ 의존성
  + Lombok
  + Spring Boot DevTools
  + Spring Web
  + Spring Data JPA
  + MariaDB Driver
  + Spring for Apache Kafka (이후에 Kafka연결)


### 2. 프로젝트 설정 변경
main/source/application.properties를 삭제하고 application.yaml을 생성하여 설정 변경
+ 서버 연결 포트 설정
+ 데이터베이스 연결 설정
+ jpa 설정


### 3. CORS설정을 위한 WebConfig 클래스 생성
웹 애플리케이션이 다른 출처(origin)에서 요청을 허용하기 위한 클래스이다
+ Spring MVC 설정을 커스터마이징하기 위해 WebMvcConfigurer 인터페이스를 implements한다
  + addCorsMappings() 메서드를 재정의하여 CORS 설정을 추가
    + registry.addMapping("/**")
      + 모든 URL 패턴 (/**)에 대해 CORS 설정
    + .allowedOrigins("http://localhost:3000")
      + http://localhost:3000에서 오는 요청만 허용
     

### 4. Entity 클래스 추가
데이터베이스와 연동이되는 클래스 이다
+ **Entity = Table**
+ Entity 클래스를 만들고 프로젝트를 실행하면 데이터베이스에 테이블이 자동으로 생성된다
![image](https://github.com/user-attachments/assets/16c9cf17-58b9-4b8b-98ae-129698f14598)
![image](https://github.com/user-attachments/assets/b79bebc0-77ad-4fe2-82d3-ec448c3c87de)

  + 2개의 데이블이 만들어지는게 하나는 Entity와 직접 연동이 되는 테이블이고  _sep가 붙는 테이블은 id를 자동으로 생성하도록 설정해서 일련번호를 저장하기위한 테이블이다


### 5. Repository 인터페이스 생성 
데이터베이스에 실질적인 CURD 작업을 수행하기 위한 인스턴스를 생성하기 위한 Repository 인터페이스이다
+ Interface를 인터페이스로 생성하여 CURD 작업을 수행한다


### 6. Service 클래스 생성
실제 사용자의 요청을 처리(CURD)하는 Service 클래스이다
+ Repository 인터페이스로 인스턴스를 생성하여 요청을 처리한다

### 7. Controller 클래스 생성
URL과 Service 클래스의 메소드를 매핑하는 Controller 클래스이다
+ Service 클래스를 인스턴스로 생성하여 매핑


### 8. DTO 클래스 생성
Controller 클래스와 Service 클래스 사이의 데이터 전달을 위한 DTO 클래스이다


### 9. 데이터 삽입 수행
+ POSTMAN API를 사용하여 데이터 삽입 수행
![image](https://github.com/user-attachments/assets/5ec389d5-3771-49d5-aab6-607596b59355)
![image](https://github.com/user-attachments/assets/b55fc816-8d92-48ac-939f-2a4bf58d306e)





+ 데이터가 삽입되었는지 데이터베이스 확인
![image](https://github.com/user-attachments/assets/d9012531-b737-42d6-a5fd-33584208ba3b)


---
## Kafka 연결
Kafka를 연결하여 데이터를 삽입할 때 Topic 전송


### 1. Kafka사용을 위한 의존성 추가 - rebuild수행
+ build.gradle - dependencies
  + **implementation 'org.springframework.kafka:spring-kafka'**


### 2. Kafka에 대한 정보 추가
Kafka사용을 위한 Kafka에 대한 정보를 SpringBoot 프로젝트 설정 파일에 추가
+ application.yaml


### 3. Kafka 환경 설정 클래스 생성
+ KafkaConfiguration


#### 3-2. DTO클래스 수정
+ bid추가


### 4. 메세지 전송 Producer 클래스 생성
+ Topic을 생성한다
+ sendMessage()로 메세지를 전달 받으면 Topic과 함께 메세지를 send()로 메세지를 전송한다


### 5. Service 클래스 수정
+ Producer 클래스를 인스턴스를 생성하여 데이터 삽입이 성공하면 Kafka로 메세지와 sendMessage()를 호출한다 

