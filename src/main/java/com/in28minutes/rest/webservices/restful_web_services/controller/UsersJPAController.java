package com.in28minutes.rest.webservices.restful_web_services.controller;

import com.in28minutes.rest.webservices.restful_web_services.entity.Post;
import com.in28minutes.rest.webservices.restful_web_services.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restful_web_services.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restful_web_services.entity.User;
import com.in28minutes.rest.webservices.restful_web_services.repository.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.repository.UserRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/jpa")
public class UsersJPAController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

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
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException(String.format("id: %d not found", id));
        }

//        List<Post> posts = user.get().getPosts();

        return ResponseEntity.ok(user.get().getPosts());
    }

    // POST /users/{id}/posts
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> addPostByUserId(@PathVariable("id") int id,
                                  @Valid @RequestBody Post post,
                                  UriComponentsBuilder builder){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException(String.format("id: %d not found", id));
        }

        post.setUser(user.get());
        post.setPostedDate(LocalDate.now());
        postRepository.save(post);

        URI location = builder.path("/jpa/users/{user_id}/posts/{post_id}")
                .buildAndExpand(user.get().getId(), post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // GET users/{id}/posts/{post_id}
    @GetMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Post> getPostsByUserIdAndPostId(@PathVariable("id") int id,
                                              @PathVariable("post_id") int postId){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException(String.format("id: %d not found", id));
        }

        List<Post> postsByUser = user.get().getPosts();

        Post post = postsByUser
                .stream()
                .filter(postItem -> postItem.getId() == postId)
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("post not found"));

        return ResponseEntity.ok(post);
    }

}
