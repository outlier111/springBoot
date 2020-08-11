package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.vo.TestApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {

    private final static Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    @Autowired
    private TestApplication testApplication;

    /*
       127.0.0.1:8085/test/logTest------------get
    */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("this is trace test");
        LOGGER.debug("this is debug test");
        LOGGER.info("this is info test");
        LOGGER.warn("this is warn test");
        LOGGER.error("this is error test");
        return "-------------this is log test---------------!";
    }

    /*
        127.0.0.1:8085/test/config------------get
     */
    @GetMapping("/config")
    @ResponseBody
    public String testApplication() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("-----")
                .append(name).append("-----")
                .append(age).append("-----")
                .append(desc).append("-----")
                .append(random).append("-----").append("<br>");
        sb.append(testApplication.getPort()).append("-----")
                .append(testApplication.getName()).append("-----")
                .append(testApplication.getAge()).append("-----")
                .append(testApplication.getDesc()).append("-----")
                .append(testApplication.getRandom()).append("-----");
        return sb.toString();
    }

    /*
        127.0.0.1:8080/test/testSpringBoot------------get
     */
    @GetMapping("/testSpringBoot")
    @ResponseBody
    public String testSpringBoot() {
        return "I hava a dream!";
    }
}
