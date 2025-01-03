package com.recruitment.service;

import com.recruitment.model.User;
import com.recruitment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User createUser ( User user ) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    public void deleteUser ( Long id ) {
        userRepository.deleteById(id);
    }


}
