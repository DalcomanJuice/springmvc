package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //내 클래스 지정
    //private final Logger log = LoggerFactory.getLogger(getClass()); //slf4j 를 하면 자동으로 넣어준다.

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        //print ln 으로 찍기
        System.out.println("name = " + name);

        //logger로 찍기
        log.trace("trace trace=" + name); // 중괄호를 해야 하는 이유.
        //위의 경우는 string 의 합 연산이 일어난다.

        //로그의 info를 알 수 있다.
        log.trace("trace trace={}", name);
        //일반 파라매터만 놓는다. <- 연산이 일어나지 않음 .. trace 의 등급의 로그가 아니어도.. 이런 문제가 발생.. 의미없는 연산

        log.debug("trace debug={}", name);
        log.info("trace info={}", name);
        log.warn("trace warn={}", name);
        log.error("trace log={}", name);
        return "ok";
    }


}
