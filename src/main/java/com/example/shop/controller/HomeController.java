package com.example.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";//반환한 문자( home )과 스프링부트 설정 prefix , suffix 정보를 사용해서 렌더링할 뷰( html )를 찾는다.
    }
}
