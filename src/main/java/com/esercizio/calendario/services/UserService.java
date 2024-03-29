package com.esercizio.calendario.services;

import com.esercizio.calendario.entities.Calendario;
import com.esercizio.calendario.entities.User;
import com.esercizio.calendario.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User addUser(User user) {
        User alreadyUser = userRepository.findByNomeAndCognome(user.getNome(), user.getCognome());
        if (alreadyUser != null) {
            throw new IllegalArgumentException();
        }
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional;
    }

    public Optional<User> updateUser(Long id,User user){
        Optional<User> userDaAggiornare = userRepository.findById(id);
        if (userDaAggiornare.isPresent()){
            userDaAggiornare.get().setNome(user.getNome());
            userDaAggiornare.get().setCognome(user.getCognome());
            userDaAggiornare.get().setDataDiNascita(user.getDataDiNascita());
            userRepository.save(userDaAggiornare.get());
        } else {
            return Optional.empty();
        }
        return userDaAggiornare;
    }


    public Optional<User> deleteUserById(Long id){
        Optional<User> userDaCancellareOptional = userRepository.findById(id);
        if(userDaCancellareOptional.isPresent()){
            userRepository.delete(userDaCancellareOptional.get());
            return userDaCancellareOptional;
        }else{
            return Optional.empty();
        }

    }
}
