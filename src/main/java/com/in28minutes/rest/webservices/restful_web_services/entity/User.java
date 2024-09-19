package com.in28minutes.rest.webservices.restful_web_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user_details")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Name cannot be null.")
    @Size(min=2, message = "Name must be at least 2 characters.")
//    @JsonProperty("user_name")
    private String name;

    @NotNull(message = "birthDate cannot be null.")
    @Past(message = "birthDate must not be in the future.")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

}
