package com.in28minutes.rest.webservices.restful_web_services.controller;

import com.in28minutes.rest.webservices.restful_web_services.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restful_web_services.entity.User;
import com.in28minutes.rest.webservices.restful_web_services.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/jpa")
public class UsersJPAController {

    @Autowired
    UserRepository userRepository;

    // GET /users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<Object> addUsers(@Valid @RequestBody User user,
                                            UriComponentsBuilder builder){
        User newUser = userRepository.save(user);
        URI location = builder.path("/jpa/users/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // add link to http:localhost:8080/users using HATEOAS

    //EntityModel
    //WebMvcLinkBuilder

    // GET /users/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException(String.format("id: %d not found", id));
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn((this.getClass())).getUsers()
        );

        entityModel.add(webMvcLinkBuilder.withRel("all-users"));

        return ResponseEntity.ok(entityModel);
    }

    // DELETE /users/{id}
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") int id){
        try{
            userRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }

    // GET /users/{id}/posts
    @GetMapping("/users/{id}/posts")
    public String getPostsByUserId(@PathVariable("id") int id){
        return String.format("get posts for user by user id: %d", id);
    }

    // POST /users/{id}/posts
    @PostMapping("/users/{id}/posts")
    public String addPostByUserId(@PathVariable("id") int id){
        return String.format("adding post by user id: %d", id);
    }

    // GET users/{id}/posts/{post_id}
    @GetMapping("/users/{id}/posts/{post_id}")
    public String getPostsByUserIdAndPostId(@PathVariable("id") int id,
                                            @PathVariable("post_id") int postId){
        return String.format("get post by user id: %d, and post_id: %d", id, postId);
    }

}
