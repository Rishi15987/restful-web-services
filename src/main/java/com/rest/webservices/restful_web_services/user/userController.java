package com.rest.webservices.restful_web_services.user;

import com.rest.webservices.restful_web_services.Exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class userController {

    private userDaoService service;
    public userController(userDaoService service){
        this.service=service;
    }
    @GetMapping("/users")
    public List<User> retreiveAllUsers(){
        return service.findAll();
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
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user==null)
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retreiveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteById(id);
    }
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User saveduser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saveduser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
