package com.rest.webservices.restful_web_services.user;

import com.rest.webservices.restful_web_services.Exception.UserNotFoundException;
import com.rest.webservices.restful_web_services.jpa.PostRepository;
import com.rest.webservices.restful_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class userJpaController {
    private UserRepository repository;
    private PostRepository postRepository;
    public userJpaController(UserRepository repository,PostRepository postRepository){
        this.repository=repository;
        this.postRepository=postRepository;
    }
    @GetMapping("/jpa/users")
    public List<User> retreiveAllUsers(){
        return repository.findAll();
    }
//    @GetMapping("/users/{id}")
//    public User retreiveUser(@PathVariable Integer id){
//        User user = service.findOne(id);
//        if(user==null){
//            throw new UserNotFoundException("id"+id);
//        }
//        return user;
//    }
    //Implemented Hateos which is used to return resource with api like here for ex its url.userController
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retreiveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        return user.get().getPosts();
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User saveduser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saveduser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id,@Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        post.setUser(user.get());
        Post savedpost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedpost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
