package com.hqyj.springcloud.springCloudClientAccount.modules.account.service.impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.dao.UserDao;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.service.UserService;
import com.hqyj.springcloud.springCloudClientAccount.modules.vo.Result;
import com.hqyj.springcloud.springCloudClientAccount.modules.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Result<User> insertUser(User user) {
        return null;
    }

    @Override
    public Result<User> login(User user) {
        return null;
    }

    @Override
    public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
        return null;
    }

    @Override
    public Result<User> updateUser(User user) {
        return null;
    }

    @Override
    public Result<Object> deleteUser(int userId) {
        return null;
    }

    @Override
    public User getUserByUserId(int userId) {
        User user = userDao.getUserByUserId(userId);
        List<City> cities = Optional.ofNullable(
                restTemplate.getForObject(
                "http://CLIENT-TEST/api/cities/{countryId}",
                List.class,522))
                .orElse(Collections.emptyList());
        user.setCities(cities);
        return user;
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        return null;
    }

   /* @Override
    @Transactional
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"用户名已被注册",user);
        }

        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);

        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()){
            userRoleDao.deleteUserRoleByUserId(user.getUserId());
            roles.stream().forEach(item->{
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(Result.ResultStatus.SUCCESS.status,"注册成功",user);
    }

    @Override
    public Result<User> login(User user) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(user.getUserName(),
                        MD5Util.getMD5(user.getPassword()));
        usernamePasswordToken.setRememberMe(user.getRememberMe());

        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILD.status,
                    "用户名或密码错误.");
        }

//        Session session = subject.getSession();
//        session.setAttribute("user", (User)subject.getPrincipal());

        return new Result<User>(Result.ResultStatus.SUCCESS.status,
                "登录成功.", user);
    }

    @Override
    public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<User>(Optional.ofNullable(
                userDao.getUserBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()){
            return new Result<User>(
                    Result.ResultStatus.FAILD.status,"无权限修改");
        }

        userDao.updateUser(user);

        List<Role> roles = user.getRoles();
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        if (roles != null && !roles.isEmpty()){
            roles.stream().forEach(item->{
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status,"修改成功",user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<Object>(
                Result.ResultStatus.SUCCESS.status,"删除成功");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Upload failed.");
        }

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", relativePath);    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }*/


}
