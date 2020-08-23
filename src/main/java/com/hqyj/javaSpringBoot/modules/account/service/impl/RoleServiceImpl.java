package com.hqyj.javaSpringBoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.dao.RoleDao;
import com.hqyj.javaSpringBoot.modules.account.dao.UserRoleDao;
import com.hqyj.javaSpringBoot.modules.account.entity.Role;
import com.hqyj.javaSpringBoot.modules.account.service.RoleService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles())
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Role> getRoleBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Role>(Optional.ofNullable(
                roleDao.getRoleBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<Role> insertRole(Role role) {
        Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());
        if (roleTemp != null){
            return new Result<Role>(Result.ResultStatus.FAILD.status,"该角色已存在",role);
        }

        role.setRoleName(role.getRoleName());
        roleDao.insertRole(role);

        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"添加成功",role);
    }

    @Override
    @Transactional
    public Result<Role> updateRole(Role role) {
        Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());
        if (roleTemp != null && roleTemp.getRoleId() != role.getRoleId()) {
            return new Result<Role>(
                    Result.ResultStatus.FAILD.status, "无权限修改");
        }
        roleDao.updateRole(role);
        userRoleDao.deleteUserRoleByUserId(role.getRoleId());
        return new Result<Role>(
                Result.ResultStatus.SUCCESS.status,"修改成功",role);
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }

    @Override
    @Transactional
    public Result<Role> deleteRole(int roleId) {
        roleDao.deleteRole(roleId);
        return new Result<Role>(
                Result.ResultStatus.SUCCESS.status,"删除成功");
    }
}
