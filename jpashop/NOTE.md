JDK 11
Spring boot starter를 통해서 프로젝트 생성


build.gradle에서 스프링 부트에 사용하는 패키지와 의존관계를 설정한다.
External Library에 라이브러리들과 그에 의존하는 라이브러리가 담겨있다.

Lombok은 중복 코드를 줄여주는 플러그인
Getter, Setter같은 것을 속성에 일일이 달아주어도 되지 않아도 됨 (Hello 클래스 참고)

./gradlew dependencies : 의존관계를 보여줌
spring-boot-starter-web
1. 내장 톰켓을 가지고 있음
2. spring-webmvc 모듈에 의존하고 있음
3. spring-core

thymeleaf : 템플릿 엔진으로 사용
data-jpa 
1. aop와 관련된것.
2. jdbc가 있음
3. HikariCP : 커넥션 풀로 사용
4. Hibernate
5. Spring data JPA 
6. 로깅은 logback을 기본으로 사용하고 있음

test
1. junit, spring-test, mockito, assertj

h2datase : 가끔 버전이 안맞아서 문제가 있을 수 있음. 다운!=사용일 때 혹시 안되면 맞춰주기
커넥션풀은 히카리씨피
웹 뷰 템플릿은 타임리프
로깅은 SLF4J에 로그백 구현체를 꽂아서 사용
테스트


Timeleaf
- JSP를 대체해서 요즘 스프링이 미는 템플릿 엔진
- 2.x대에서는 <br>같은거를 안닫아주면 에러가 났었음


dependency에 devtools를 추가하면 캐싱 등을 사용하여 개발 환경에서 용이해진다

서버 재시작을 안하고 변경 후 빌드->리컴파일 하면 서버 재시작 안하고 변경사항을 확인할수있다.


H2 데이터베이스
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 콘솔 UI를 제공함
Java로 실행됨, /h2/bin에서 터미널명령 ./h2.sh로 실행
http://localhost:8082/login.jsp?jsessionid=54e3c4f4afbf20d741d349677f3f76ca
jdbc:h2:~/test 해당 경로에 mv.db가 생성된 것을 확인하면 JDBC URL에 TCP 모드로 접속할 수 있다.
jdbc:h2:tcp://localhost/~/test를 JDBC URL에 입력하기

Member Entity를 만들고, Repository를 통해 연산한다.
테스트 코드를 작성해서 테스트 가능
빌드 후 배포파일 실행해서 최종확인