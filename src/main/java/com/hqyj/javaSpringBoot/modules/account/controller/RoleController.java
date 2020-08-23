package com.hqyj.javaSpringBoot.modules.account.controller;


import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.Role;
import com.hqyj.javaSpringBoot.modules.account.entity.User;
import com.hqyj.javaSpringBoot.modules.account.service.RoleService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*
    127.0.0.1/api/roles------------get
     */
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /*
    127.0.0.1/api/roles------------post
     */
    @PostMapping(value = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> getUserBySearchVo(@RequestBody SearchVo searchVo) {
        return roleService.getRoleBySearchVo(searchVo);
    }

    /*
    127.0.0.1/api/role------------post
    {"userName":"admin"}
     */
    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> insertRole(@RequestBody Role role) {
        return roleService.insertRole(role);
    }

    /*
    127.0.0.1/api/role------------put
     */
    @PutMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    /*
    127.0.0.1/api/role/{roleId}------------get
     */
    @GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId){
        return roleService.getRoleByRoleId(roleId);
    }

    /*
    127.0.0.1/api/role/{roleId}------------delete
     */
    @DeleteMapping("/role/{roleId}")
    public Result<Role> deleteRole(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }
}
