package com.in28minutes.rest.webservices.restful_web_services.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//@JsonIgnoreProperties("field1") // one way to remove property from response
@JsonFilter("SomeBeanFilter") // needed for dynamic filtering
public class SomeBean {

    private String field1;

//    @JsonIgnore // another way to remove property from response
    private String field2;

    private String field3;
}
