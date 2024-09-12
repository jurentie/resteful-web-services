package com.in28minutes.rest.webservices.restful_web_services.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private LocalDate birthDate;
//    private List<Post> posts;

}
