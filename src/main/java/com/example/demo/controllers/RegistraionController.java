package com.example.demo.controllers;

import com.example.demo.models.Client;
import com.example.demo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistraionController {
    private final ClientService clientService;

    public RegistraionController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping
    public ResponseEntity<?> register(@RequestBody Client client){
        boolean answer=clientService.add(client);
        if (!answer){
            return ResponseEntity.ok("User is not added");
        }else {
            return ResponseEntity.ok("User added");
        }
    }
}
