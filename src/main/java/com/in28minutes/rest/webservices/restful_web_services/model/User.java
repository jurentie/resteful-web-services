package com.in28minutes.rest.webservices.restful_web_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    @NotNull(message = "Name cannot be null.")
    @Size(min=2, message = "Name must be at least 2 characters.")
    @JsonProperty("user_name")
    private String name;

    @NotNull(message = "birthDate cannot be null.")
    @Past(message = "birthDate must not be in the future.")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

//    private List<Post> posts;

}
