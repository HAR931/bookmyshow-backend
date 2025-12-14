package com.example.demo.service;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public void addUser(RegisterDTO registerDTO){

        User user=new User();
        user.setName(registerDTO.getName());
        user.setPhone(registerDTO.getPhone());
        user.setEmail (registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));;

        userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Integer findIdByMail(String email){
        return userRepository.findIdByMail(email);
    }

}
