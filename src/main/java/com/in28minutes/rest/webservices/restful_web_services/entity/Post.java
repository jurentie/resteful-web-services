package com.in28minutes.rest.webservices.restful_web_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=10, max=50, message = "description must be between 10 and 50 characters long.")
    private String description;

    private LocalDate postedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
