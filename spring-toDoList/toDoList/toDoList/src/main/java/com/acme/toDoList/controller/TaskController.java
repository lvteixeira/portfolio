package com.acme.toDoList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @GetMapping
    public ResponseEntity<String> getAllTasks() {
        return ResponseEntity.status(200).body("getAllTasks Endpoint");
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getTaskById(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body("getTaskById Endpoint");
    }

    @PostMapping
    public ResponseEntity<String> createTask() {
        return ResponseEntity.status(201).body("createTask Endpoint");
    }

    @PutMapping
    public ResponseEntity<String> updateTask() {
        return ResponseEntity.status(204).body("updateTask Endpoint");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTask() {
        return ResponseEntity.status(204).body("deleteTask Endpoint");
    }
}
