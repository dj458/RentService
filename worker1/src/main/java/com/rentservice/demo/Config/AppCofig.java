package com.rentservice.demo.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.uberrent.core",excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern={"JmsConfig","JmsConfig"}))
public class AppCofig {

}
