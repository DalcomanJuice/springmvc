package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestPAramV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // return string 값을 responebody 응답 메세지에 넣는다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("v2 log username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody //request param 생략도 가능하다.대신 조건으로 변수명과 매핑 string 이름이 일치해야 한다. 차이점은 v2와 비교한다.
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("v3 log username={}, age={}", username, age);
        return "ok";
    }

    //RequestParam 을 생략할 경우 스프링 프레임워크 자체에서 required 를 false 로 설정한다.
    //애노테이션 샐약도 좋긴 하지만... 너무 생략하는것도 안좋다. 먼가 해당 변수가 무엇인지 직관적이기 않기 때문에..₩
    @ResponseBody //request param 생략도 가능하다.대신 조건으로 변수명과 매핑 string 이름이 일치해야 한다. 차이점은 v3와 비교한다.
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){

        log.info("v4 log username={}, age={}", username, age);
        return "ok";
    }

    //Required true - http 요청 할 경우 무줘건 해당 내용을 전달 해야 한다.
    //Required false - http 요청 할 경우 해당 내용을 보내지 않아도 됩니다.
    //400 error <- 파라미터 오류
    //500 error <- Null // int 는 Null을 못받는다. 그렇기 때문에 null을 받을 수 있게 Integer로 받자
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            //@RequestParam(required = false) int age){
            @RequestParam(required = false) Integer age){

        //null, ""; 는 다르다.
        //username= 비어있으면 ""이기 때문에 그냥 통과 된다.

        log.info("required log username={}, age={}", username, age);
        return "ok";
    }

    //default value 해당 값이 아니면.. dafault value로 넣겠다.
    //default value 가 있으면 해당 value가 있든 없든 필요 없다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            //@RequestParam(required = false) int age){
            @RequestParam(required = false, defaultValue = "-1") Integer age){

        //null, ""; 는 다르다.
        //username= 비어있으면 ""이기 때문에 그냥 통과 된다.
        log.info("default log username={}, age={}", username, age);
        return "ok";
    }

    //pram map으로 가져오기 / multimap 과 차이점도 알고 있자.!!
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(@RequestParam Map<String, Object> paramMap){
        log.info("map log username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
