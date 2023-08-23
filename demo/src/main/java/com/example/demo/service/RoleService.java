package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.reposetories.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public ResponseEntity<Role> getById(long id){
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Role not exists with id: " + id));

        return ResponseEntity.ok(role);
    }

    public Role add(@Valid Role role){
        return roleRepository.save(role);
    }

    public ResponseEntity<Role> update(@Valid Role role, long id){
        Role updated = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Role not exists with id: " + id));

        updated.setName(role.getName());
        roleRepository.save(updated);

        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<Role> delete(long id){
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Role not exists with id: " + id));
        roleRepository.delete(role);

        return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
    }


}
