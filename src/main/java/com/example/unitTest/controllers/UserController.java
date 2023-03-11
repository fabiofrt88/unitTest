package com.example.unitTest.controllers;

import com.example.unitTest.entities.UserEntity;
import com.example.unitTest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    // crea un utente
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserEntity user){
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("L'utente è stato creato");
    }

    // Restituisce la lista di utenti
    @GetMapping({"", "/"})
    public List<UserEntity> getPazienti(){
        return userService.getAllUser();
    }

    // restituisce un utente a partire dall'id
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        Optional<UserEntity> user = userService.getUserById(id);
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. Please check your id");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateUserById(@RequestBody UserEntity userMod, @PathVariable Long id) {
        if(userMod == null || id == null) {
            throw new IllegalArgumentException("Bad Request - Error request body");
        }
        userService.updateUserById(userMod, id);
        return ResponseEntity.status(HttpStatus.OK).body("Utente modificato correttamente");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteUser() {
        userService.deleteAllUser();
        return ResponseEntity.status(HttpStatus.OK).body("Utenti cancellati correttamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        if(id == null) throw new IllegalArgumentException("Bad Request - Error id request param");
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Utente cancellato correttamente");
    }

}
