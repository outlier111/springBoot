package com.hqyj.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.Role;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    PageInfo<Role> getRoleBySearchVo(SearchVo searchVo);

    Result<Role> insertRole(Role role);

    Result<Role> updateRole(Role role);

    Role getRoleByRoleId(int roleId);

    Result<Role> deleteRole(int roleId);
}
