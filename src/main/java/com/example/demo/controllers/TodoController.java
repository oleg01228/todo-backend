package com.example.demo.controllers;

import com.example.demo.models.Client;
import com.example.demo.models.Todo;
import com.example.demo.repo.ClientRepo;
import com.example.demo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final ClientRepo clientRepo;

    public TodoController(TodoService todoService, ClientRepo clientRepo) {
        this.todoService = todoService;
        this.clientRepo = clientRepo;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestBody String username) {
        Client client = clientRepo.findByUsername(username);
        if (client != null) {
            return (ResponseEntity<?>) client.getTodos();
        } else {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }
    @PostMapping("/{username}")
    public Todo addTodo(@RequestBody Todo todo, @PathVariable(name = "username")String username){
        todo.setClient(clientRepo.findByUsername(username));
        return todoService.add(todo);
    }

}
