package com.brienn.testing.services;

import com.brienn.testing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brienn.testing.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public User addUser(User newUser){
        return repository.save(newUser);
    }

    public User updateUser(Long id, User newUser){
        return repository.findById(id).map(user -> {
            user.setFirst_name(newUser.getFirst_name());
            user.setLast_name(newUser.getLast_name());

            return repository.save(user);
        }).orElseThrow(RuntimeException::new);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

}