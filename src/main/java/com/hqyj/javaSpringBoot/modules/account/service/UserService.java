package com.hqyj.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.User;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUserBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int userId);

    User getUserByUserId(int userId);

    Result<String>uploadUserImg(MultipartFile file);

    User getUserByUserName(String userName);
}
