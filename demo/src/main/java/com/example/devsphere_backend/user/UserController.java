package com.example.devsphere_backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        System.out.println(userService.getUsers());
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        System.out.println(user);
        userService.postUsers(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/exists")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email){
        Optional<User> existingUser = userService.getUserWithEmail(email);

        // If the user is found, return the user object
        if (existingUser.isPresent()) {
            return ResponseEntity.ok(existingUser.get());
        }
        // If not found, return a 404 Not Found status
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
    }
}
