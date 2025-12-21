package com.example.demo.controller;

import com.example.demo.DTO.TheatreDTO;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TheatreService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/theatre")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@Valid @RequestBody TheatreDTO theatreDTO, Principal principal) {

        String userEmail= principal.getName();

        User loggedInUser = userRepository.findByEmail(userEmail);

        theatreService.addTheatre(theatreDTO,loggedInUser);

        return ResponseEntity.ok("New Theatre has been added successfully");
    }
}
