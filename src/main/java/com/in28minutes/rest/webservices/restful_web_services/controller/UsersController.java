package com.in28minutes.rest.webservices.restful_web_services.controller;

import com.in28minutes.rest.webservices.restful_web_services.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restful_web_services.model.User;
import com.in28minutes.rest.webservices.restful_web_services.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    UserDaoService userDaoService;

    // GET /users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userDaoService.findAll());
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<Object> addUsers(@RequestBody User user,
                                            UriComponentsBuilder builder){
        int userId = userDaoService.addUser(user);
        URI location = builder.path("/users/{id}").buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }

    // GET /users/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userDaoService.getUserById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("id: %d not found", id));
        }

        return ResponseEntity.ok(userDaoService.getUserById(id));
    }

    // DELETE /users/{id}
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") int id){
        return ResponseEntity.ok(userDaoService.deleteUserById(id));
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
