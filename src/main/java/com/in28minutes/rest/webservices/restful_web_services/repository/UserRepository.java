package com.in28minutes.rest.webservices.restful_web_services.repository;

import com.in28minutes.rest.webservices.restful_web_services.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
