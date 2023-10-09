package com.acme.toDoList.controller;

import com.acme.toDoList.dto.UserDTO;
import com.acme.toDoList.model.UserEntity;
import com.acme.toDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserEntity> users = userService.getAll();
        List<UserDTO> dtos = userService.convertEntityListToDTOList(users);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        Optional<UserEntity> user = userService.findById(id);

        if (user.isPresent()) {
            UserDTO dto = userService.convertEntityToDTO(user.get());
            return ResponseEntity.status(200).body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO createPayload) {
        UserEntity obj = userService.convertDTOToEntity(createPayload);
        userService.add(obj);
        UserDTO createUser = userService.convertEntityToDTO(obj);
        return ResponseEntity.status(201).body(createUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") int id, @RequestBody UserDTO updatePayload) {
        Optional<UserEntity> existingUser = userService.findById(id);

        if (existingUser.isPresent()) {
            UserEntity obj = userService.convertDTOToEntity(updatePayload);
            userService.update(obj, id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        Optional<UserEntity> userToDelete = userService.findById(id);

        if (userToDelete.isPresent()) {
            userService.delete(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
