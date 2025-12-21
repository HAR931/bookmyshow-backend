package com.example.demo.controller;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {

        String phone = registerDTO.getPhone();
        String email = registerDTO.getEmail();

        if (userRepository.findByEmail(email)!=null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }

        if (userRepository.findByPhone(phone)!=null) {
            return new ResponseEntity<>("Phone number already exists", HttpStatus.CONFLICT);
        }

        userService.addUser(registerDTO);

        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?>loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        String email = loginDTO.getEmail();
        String password=loginDTO.getPassword();

        User user=userRepository.findByEmail(email);

        if(user==null){
            return new ResponseEntity<>("User not Registered",HttpStatus.UNAUTHORIZED);
        }

        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("Incorrect Password",HttpStatus.UNAUTHORIZED);
        }

        String token=jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }
}
