package com.esercizio.calendario.controllers;

import com.esercizio.calendario.entities.User;
import com.esercizio.calendario.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/add")
    public ResponseEntity<User> aggiungiUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getlist")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers =   userService.getAllUser();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        Optional<User> userOptional = userService.getUser(id);
        return ResponseEntity.ok().body(userOptional);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user,@PathVariable Long id){
        Optional<User> userOptional = userService.updateUser(id,user);
        if(userOptional.isPresent()){
            return  ResponseEntity.ok().body(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/deleteid")
    public ResponseEntity<Optional<User>> deleteUserById(@RequestParam Long id){
        Optional<User> userDaCancellare = userService.deleteUserById(id);
        if(userDaCancellare.isPresent()){
            return ResponseEntity.ok().body(userDaCancellare);
        }
        return ResponseEntity.notFound().build();

    }
}
