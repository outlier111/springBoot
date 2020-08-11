package com.hqyj.javaSpringBoot.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.dao.CityDao;
import com.hqyj.javaSpringBoot.modules.test.entity.City;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList()));
    }
}
