package com.hqyj.javaSpringBoot.modules.account.controller;


import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.Resource;
import com.hqyj.javaSpringBoot.modules.account.entity.Role;
import com.hqyj.javaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.javaSpringBoot.modules.account.service.RoleService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
    127.0.0.1/api/resources------------get
     */
    @GetMapping("/resources")
    public List<Resource> getResources() {
        return resourceService.getResources();
    }

    /*
    127.0.0.1/api/resources------------post
     */
    @PostMapping(value = "/resources", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Resource> getUserBySearchVo(@RequestBody SearchVo searchVo) {
        return resourceService.getResourceBySearchVo(searchVo);
    }

    /*
    127.0.0.1/api/resource------------post
    {"userName":"admin"}
     */
    @PostMapping(value = "/resource", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> insertResource(@RequestBody Resource resource) {
        return resourceService.insertResource(resource);
    }

    /*
    127.0.0.1/api/resource------------put
     */
    @PutMapping(value = "/resource", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> updateResource(@RequestBody Resource resource) {
        return resourceService.updateResource(resource);
    }

    /*
    127.0.0.1/api/resource/{resourceId}------------get
     */
    @GetMapping("/resource/{resourceId}")
    public Resource getResourceByResourceId(@PathVariable int resourceId){
        return resourceService.getResourceByResourceId(resourceId);
    }

    /*
    127.0.0.1/api/resource/{resourceId}-------------delete
     */
    @DeleteMapping("/resource/{resourceId}")
    public Result<Resource> deleteresource(@PathVariable int resourceId) {
        return resourceService.deleteResource(resourceId);
    }
}
