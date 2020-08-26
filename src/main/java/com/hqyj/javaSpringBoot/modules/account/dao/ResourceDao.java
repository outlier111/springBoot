package com.hqyj.javaSpringBoot.modules.account.dao;

import com.hqyj.javaSpringBoot.modules.account.entity.Resource;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourceDao {

    @Select("select * from resource r left join role_resource rr " +
            "on r.resource_id = rr.resource_id where rr.role_id = #{roleId}")
    List<Resource> getResourceByRoleId(int roleId);

    @Select("select * from resource")
    List<Resource> getResources();

    @Select("<script>" +
            "select * from resource "
            + "<where> "
            + "<if test='keyWord !=\"\" and keyWord != null'>"
            + " and (resource_name like '%${keyWord}%')"
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by resource_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Resource> getResourceBySearchVo(SearchVo searchVo);

    @Insert("insert into resource (resource_uri,resource_name,permission)" +
            " values (#{resourceName},#{resourceUri},#{permission})")
    @Options(useGeneratedKeys = true, keyColumn = "resource_id", keyProperty = "resourceId")
    void insertResource(Resource resource);

    @Select("select * from resource where resource_id = #{resourceId}")
    @Results(id = "ResourceResult",value = {
            @Result(column = "resource_id", property = "resourceId"),
            @Result(column = "resource_id", property = "roles",
                    javaType = List.class,
                    many = @Many(select =
                            "com.hqyj.javaSpringBoot.modules.account.dao.RoleDao.getRoleByResourceId"))})
    Resource getResourceByResourceId(int resourceId);

    @Update("update resource set resource_uri = #{resourceUri}, resource_name = #{resourceName}," +
            " permission = #{permission} where resource_id = #{resourceId}")
    void updateResource(Resource resource);

    @Select("select * from resource where resource_name = #{resourceName}")
    @ResultMap(value = "ResourceResult")
    Resource getResourceByResourceName(String resourceName);

    @Delete("delete from resource where resource_id = #{resourceId}")
    void deleteResource(int resourceId);
}
