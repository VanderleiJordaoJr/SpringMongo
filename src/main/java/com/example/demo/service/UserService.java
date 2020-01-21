package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(final String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByCpfCnpj(final String cpfCnpj) {
        return userRepository.findByCpfCnpj(cpfCnpj);
    }

    public User save(final User user) {
        return userRepository.save(user);
    }

    public void destroy(final String id) {
        userRepository.deleteById(id);
    }
}
