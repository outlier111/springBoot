package com.hqyj.springcloud.springCloudClientAccount.modules.account.service;

import com.hqyj.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.hqyj.springcloud.springCloudClientAccount.modules.account.service.impl.TestFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "CLIENT-TEST", fallback = TestFeignClientFallback.class)
@Primary
public interface TestFeignClient {

    @GetMapping("/api/cities/{countryId}")
    List<City> getCityByCountryId(@PathVariable int countryId);


}
