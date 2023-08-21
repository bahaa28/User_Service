package com.example.demo.Controllers;

import com.example.demo.model.UserEntity;
import com.example.demo.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserEntityController {

    @Autowired
    UserEntityService userEntityService;

    @GetMapping
    public List<UserEntity> getAll(){
        return userEntityService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable long id){
        return userEntityService.getById(id);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserEntity> getByUsername(@PathVariable String username){
        return userEntityService.getByUsername(username);
    }

    @PostMapping
    public UserEntity add(@RequestBody UserEntity userEntity){
        return userEntityService.add(userEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> update(
            @PathVariable long id,
            @RequestBody UserEntity userEntity
    ){
        return userEntityService.update(userEntity, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserEntity> delete(@PathVariable long id){
        return userEntityService.delete(id);
    }

}
