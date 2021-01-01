package com.example.demo.service;

import com.example.demo.models.Client;
import com.example.demo.models.Role;
import com.example.demo.repo.ClientRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class ClientService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ClientRepo clientRepo;

    public ClientService(BCryptPasswordEncoder bCryptPasswordEncoder, ClientRepo clientRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.clientRepo = clientRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client = clientRepo.findByUsername(s);
        if (client != null) {
            return client;
        } else {
            throw new UsernameNotFoundException("Not found.");
        }
    }
    public  boolean add(Client client){
        Client clientFromDb=clientRepo.findByUsername(client.getUsername());
        if (clientFromDb!=null){
            return false;
        }else {
            client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
            client.setRoles(Collections.singleton(new Role(1L,"ROLE_USER")));
            clientRepo.save(client);
            return true;
        }
    }
}
