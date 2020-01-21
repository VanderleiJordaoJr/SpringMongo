package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findAllUsers();
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            user.set_id(ObjectId.get().toString());
            User createUser = userService.save(user);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/user/")
    public ResponseEntity<User> findByCpfCnpj(@RequestParam(name = "cpfcnpj") String cpfCnpj) {
        Optional<User> optionalUser = userService.findByCpfCnpj(cpfCnpj);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateById(@PathVariable String id, @RequestBody User user) {
        user.set_id(id);
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteById(@PathVariable String id) {
        userService.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
