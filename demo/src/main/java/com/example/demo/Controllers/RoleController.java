package com.example.demo.Controllers;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getById(@PathVariable long id){
        return roleService.getById(id);
    }

    @PostMapping
    public Role add(@RequestBody Role role){
        return roleService.add(role);
    }

    @PutMapping("{id}")
    public ResponseEntity<Role> update(
            @PathVariable long id,
            @RequestBody Role role
    ){
        return roleService.update(role, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Role> delete(@PathVariable long id){
        return roleService.delete(id);
    }

}
