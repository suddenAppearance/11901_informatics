package ru.itis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> listUsers() {
        return ResponseEntity.ok(usersService.all());
    }

    @GetMapping("/users/{pk}")
    public ResponseEntity<UserDto> userInstance(@PathVariable("pk") Long pk){
        return ResponseEntity.ok(usersService.findById(pk));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserForm userForm){
        return ResponseEntity.ok(usersService.save(User.from(userForm)));
    }

    @PutMapping("/users/{pk}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("pk") Long pk, @RequestBody UserForm userForm){
        return ResponseEntity.ok(usersService.update(pk, userForm));
    }

    @DeleteMapping("/users/{pk}")
    public ResponseEntity<?> deleteUser(@PathVariable("pk") Long pk){
        usersService.delete(pk);
        return ResponseEntity.ok().build();
    }
}
