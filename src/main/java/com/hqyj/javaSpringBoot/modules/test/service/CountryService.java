package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);
}
