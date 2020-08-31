package com.hqyj.springcloud.springCloudClientTest.modules.test.controller;


import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.hqyj.springcloud.springCloudClientTest.modules.test.entity.City;
import com.hqyj.springcloud.springCloudClientTest.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityService cityService;

    /*
    127.0.0.1/api/cities/522------------get
    查询ID为522的城市
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }

    /*
    127.0.0.1/api/cities/522------------post
    {"currentPage":"1","pageSize":"5"}
     */
//    value：     指定请求的实际地址        consumes： 指定处理请求的提交内容类型（Content-Type）
    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(
            @PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }

    /*
    127.0.0.1/api/cities------------post
    {"currentPage":"1","pageSize":"5","keyWord":"SH","orderBy":"city_name","sort":"desc"}
     */
    @PostMapping(value = "/cities", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(searchVo);
    }

    /*
    127.0.0.1/api/city------------post
    {"cityName":"test1","localCityName":"freeCity","countryId":"522"}
   */
    @PostMapping(value = "/city", consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city) {
        return cityService.insertCity(city);
    }

    /*
    127.0.0.1/api/city------------put
    "cityId"="2259","cityName"="aaaa"
   */
    @PutMapping(value = "/city", consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city) {
        return cityService.updateCity(city);
    }

    /*
   127.0.0.1/api/city/2258------------delete
   "cityId"="2259","cityName"="aaaa"
  */
    @DeleteMapping("/city/{cityId}")
    public Result<Object> deleteCity(@PathVariable int cityId) {
        return cityService.deleteCity(cityId);
    }
}
