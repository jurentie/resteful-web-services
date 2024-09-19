package com.in28minutes.rest.webservices.restful_web_services.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservices.restful_web_services.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filtering(){
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering(){

        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("field3"));
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}
