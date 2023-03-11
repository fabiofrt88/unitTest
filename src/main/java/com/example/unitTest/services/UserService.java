package com.example.unitTest.services;

import com.example.unitTest.entities.UserEntity;
import com.example.unitTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    // Creazione utente
    public UserEntity createUser(UserEntity user) {
        user.setId(null);
        return userRepository.saveAndFlush(user);
    }

    // Restituisce la lista di utenti
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    // Restituisce l'utente passando l'id
    public Optional<UserEntity> getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user;
        }
        return Optional.empty();
    }

    // Modifica l'utente
    public UserEntity updateUserById(UserEntity userMod, Long id){
        if(userMod == null) throw new IllegalArgumentException();
        if(!userRepository.existsById(id));

        UserEntity user = userRepository.findById(id).get();

        if(userMod.getUserName() != null) user.setUserName(userMod.getUserName());
        if(userMod.getEmail() != null) user.setEmail(userMod.getEmail());
        if(userMod.getPassword() != null) user.setPassword(userMod.getPassword());

        return userRepository.saveAndFlush(user);
    }

    //Cancella fisicamente l'utente -- cancellazione logica? in tal caso inserire stato della cancellazione
    public void deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else throw new IllegalArgumentException();
    }

    //Cancella fisicamente tutti gli utenti -- cancellazione logica? in tal caso inserire stato della cancellazione
    public void deleteAllUser(){
        userRepository.deleteAll();
    }

}