package com.example.devsphere_backend.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void postUsers(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUserWithEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
