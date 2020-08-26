package com.hqyj.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.Resource;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

public interface ResourceService {

    List<Resource> getResources();

    PageInfo<Resource> getResourceBySearchVo(SearchVo searchVo);

    Result<Resource> insertResource(Resource resource);

    Result<Resource> updateResource(Resource resource);

    Resource getResourceByResourceId(int resourceId);

    Result<Resource> deleteResource(int resourceId);

    List<Resource> getResourceByRoleId(int roleId);
}
