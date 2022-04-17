package hello.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

//slf4j
//로그를 인터페이스화 한 프로젝트가 나왔다.
//여러분들이 라이브러리만 바꾸면 알아서 바꿀수 있는 프로젝트를 만들게됨.

//그 구현체중 하나로 logback 을 선택