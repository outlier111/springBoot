package com.hqyj.springcloud.springCloudClientTest.modules.test.service.impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.hqyj.springcloud.springCloudClientTest.modules.test.dao.CityDao;
import com.hqyj.springcloud.springCloudClientTest.modules.test.entity.City;
import com.hqyj.springcloud.springCloudClientTest.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesByCountryId(int countryId) {
//        return cityDao.getCitiesByCountryId(countryId);
        return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,
                "Insert success.", city);
    }

    @Override
    @Transactional
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,
                "Update success", city);
    }

    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,
                "Delete success.");
    }
}
