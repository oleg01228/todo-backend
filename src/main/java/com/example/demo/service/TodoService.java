package com.example.demo.service;

import com.example.demo.models.Todo;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepo todoRepo;
    private final ClientRepo clientRepo;
    public TodoService(TodoRepo todoRepo, ClientRepo clientRepo) {
        this.todoRepo = todoRepo;
        this.clientRepo = clientRepo;
    }
    public Todo add(Todo todo){
        return todoRepo.save(todo);
    }
    public void delete(Todo todo){
        todoRepo.delete(todo);
    }


}
