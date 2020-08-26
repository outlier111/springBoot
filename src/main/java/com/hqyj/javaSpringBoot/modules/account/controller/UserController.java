package com.hqyj.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.User;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    127.0.0.1/api/user----------post
    {"userName":"admin","password":"123456"}
     */
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /*
    127.0.0.1/api/login----------post
    {"userName":"admin","password":"123456"}
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user) {
        return userService.login(user);
    }

    /*
    127.0.0.1/api/users----------post
    {"currentPage":"1","pageSize":"5","keyword":"ji"}
     */
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUserBySearchVo(@RequestBody SearchVo searchVo) {
        return userService.getUserBySearchVo(searchVo);
    }

    /*
        127.0.0.1/api/user----------put
        {"userName":"jige0","userImg":"/aaa.jpg","userId":"2"}
     */
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /*
    127.0.0.1/api/user/2-------------delete
     */
    @DeleteMapping("/user/{userId}")
    @RequiresPermissions(value = "/api/user")
    public Result<Object> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    /*
    127.0.0.1/api/user/8------------get
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }

    /**
     * 127.0.0.1/api/userImg ---- post
     */

}
