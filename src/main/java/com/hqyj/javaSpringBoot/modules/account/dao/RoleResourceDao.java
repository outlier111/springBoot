package com.hqyj.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleResourceDao {

    @Delete("delete from role_resource where resource_id = #{resourceId}")
    void deleteRoleResourceByResourceId(int resourceId);

    @Insert("insert into role_resource (role_id, resource_id) " +
            "values (#{roleId}, #{resourceId})")
    void insertRoleResource(int roleId, int resourceId);
}
