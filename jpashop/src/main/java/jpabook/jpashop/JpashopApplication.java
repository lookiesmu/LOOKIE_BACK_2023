package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setData("hello world");
		String data = hello.getData();
		System.out.println(data);
		SpringApplication.run(JpashopApplication.class, args);

		//최종 잘되는지 확인
		// 빌드 :: ./gradlew clean build
		// 실행 :: /build/libs로 이동 -> java -jar jpashop-0.0.1-SNAPSHOT.jar
		//이게 배포파일이 된당


	}

}


