package com.hqyj.javaSpringBoot.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {
    /*
        127.0.0.1:8080/test/testSpringBoot------------get
     */
    @GetMapping("/testSpringBoot")
    @ResponseBody
    public String testSpringBoot(){
        return "I hava a dream!";
    }
}
