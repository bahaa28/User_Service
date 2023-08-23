package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.UserEntity;
import com.example.demo.reposetories.RoleRepository;
import com.example.demo.reposetories.UserEntityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserEntity> getAll(){
        return userEntityRepository.findAll();
    }

    public ResponseEntity<UserEntity> getById(long id){
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not exists with id: " + id));
        return ResponseEntity.ok(userEntity);
    }

    public ResponseEntity<UserEntity> getByUsername(@Valid String username){
        UserEntity userEntity = userEntityRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User not exists with username: " + username));
        return ResponseEntity.ok(userEntity);
    }

    public UserEntity add(@Valid UserEntity userEntity){
        return userEntityRepository.save(userEntity);
    }

    public ResponseEntity<UserEntity> update(@Valid UserEntity userEntity, long id){
        UserEntity updated = userEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not exists with id: " + id));

        updated.setUsername(userEntity.getUsername());
        updated.setPassword(userEntity.getPassword());
        updated.setFirstName(userEntity.getFirstName());
        updated.setLastName(userEntity.getLastName());
        updated.setBirthDate(userEntity.getBirthDate());
        updated.setUser_role(userEntity.getUser_role());
        updated.setFollowers(userEntity.getFollowers());
        updated.setFollow(userEntity.getFollow());

        userEntityRepository.save(updated);

        return ResponseEntity.ok(updated);

    }

    public ResponseEntity<UserEntity> delete(long id){
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not exists with id: " + id));
        userEntityRepository.delete(userEntity);

        return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<UserEntity> addRole(@Valid Role role, long user_id){
        UserEntity userEntity = userEntityRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User does not exists with id: " + user_id));

        userEntity.addRole(role);

        userEntityRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    public ResponseEntity<UserEntity> addRole(long role_id, long user_id){
        UserEntity userEntity = userEntityRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User does not exists with id: " + user_id));

        Role role = roleRepository.findById(role_id).orElseThrow(() ->
                new ResourceNotFoundException("role does not exists with id: " + role_id));

        userEntity.addRole(role);

        userEntityRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    public ResponseEntity<String> addList(@Valid List<UserEntity> userEntityList){
        for(UserEntity userEntity : userEntityList){
            userEntityRepository.save(userEntity);
        }
        return ResponseEntity.ok("the users added successfully");
    }




}
