package com.hqyj.javaSpringBoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.dao.ResourceDao;
import com.hqyj.javaSpringBoot.modules.account.dao.RoleResourceDao;
import com.hqyj.javaSpringBoot.modules.account.entity.Resource;
import com.hqyj.javaSpringBoot.modules.account.entity.Role;
import com.hqyj.javaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> getResources() {
        return Optional.ofNullable(resourceDao.getResources())
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Resource> getResourceBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Resource>(Optional.ofNullable(
                resourceDao.getResourceBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<Resource> insertResource(Resource resource) {
        Resource resourceTemp = resourceDao.getResourceByResourceName(resource.getResourceName());
        if (resourceTemp != null){
            return new Result<Resource>(Result.ResultStatus.FAILD.status,"该角色已存在",resource);
        }

        resourceDao.insertResource(resource);

        List<Role> roles = resource.getRoles();
        if (roles != null && !roles.isEmpty()){
            roleResourceDao.deleteRoleResourceByResourceId(resource.getResourceId());
            roles.stream().forEach(item->{
                roleResourceDao.insertRoleResource(item.getRoleId(), resource.getResourceId());
            });
        }

        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"添加成功",resource);
    }

    @Override
    @Transactional
    public Result<Resource> updateResource(Resource resource) {
        Resource resourceTemp = resourceDao.getResourceByResourceId(resource.getResourceId());
        if (resourceTemp != null && resourceTemp.getResourceId() != resource.getResourceId()) {
            return new Result<Resource>(
                    Result.ResultStatus.FAILD.status, "无权限修改");
        }
        // 添加 resource

            resourceDao.updateResource(resource);
            List<Role> roles = resource.getRoles();
            roleResourceDao.deleteRoleResourceByResourceId(resource.getResourceId());
            if (roles != null && !roles.isEmpty()){
                roles.stream().forEach(item->{
                    roleResourceDao.insertRoleResource(resource.getResourceId(), item.getRoleId());
                });
            }

        return new Result<Resource>(
                Result.ResultStatus.SUCCESS.status,"修改成功",resource);
    }

    @Override
    public Resource getResourceByResourceId(int resourceId) {
        return resourceDao.getResourceByResourceId(resourceId);
    }

    @Override
    @Transactional
    public Result<Resource> deleteResource(int resourceId) {
        resourceDao.deleteResource(resourceId);
        return new Result<Resource>(
                Result.ResultStatus.SUCCESS.status,"删除成功");
    }

    @Override
    public List<Resource> getResourceByRoleId(int roleId) {
        return resourceDao.getResourceByRoleId(roleId);
    }
}
