package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.entity.City;
import com.hqyj.javaSpringBoot.modules.test.entity.Country;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import com.hqyj.javaSpringBoot.modules.test.service.CountryService;
import com.hqyj.javaSpringBoot.modules.test.vo.TestApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    /*
       127.0.0.1/test/index-------------get
    */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap){
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo",
                "/upload/1111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/city");
        //返回碎片组装器
        return "index";
    }

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

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request,
                           @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc." + paramValue + "==" + paramValue2;
    }



}
