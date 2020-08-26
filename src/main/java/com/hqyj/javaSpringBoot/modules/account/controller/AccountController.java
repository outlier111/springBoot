package com.hqyj.javaSpringBoot.modules.account.controller;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/account")
public class AccountController {

    /*
    127.0.0.1/account/login----------get
     */
    @GetMapping("/login")
    public String loginPage(){
        return "indexSimple";
    }

    /*
    127.0.0.1/account/register----------get
     */
    @GetMapping("/register")
    public String registerPage(){

        return "indexSimple";
    }

    /*
    127.0.0.1/account/users----------get
     */
    @GetMapping("/users")
    public String userPage(){
        return "index";
    }

    /*
    127.0.0.1/account/users----------get
     */
    @GetMapping("/roles")
    public String rolePage(){
        return "index";
    }

    /*
    127.0.0.1/account/resources----------get
     */
    @GetMapping("/resources")
    public String resourcePage(){
        return "index";
    }

    /*
    127.0.0.1/account/profile-----------get
     */
    @GetMapping("profile")
    public String profilePage(){
        return "index";
    }

}
