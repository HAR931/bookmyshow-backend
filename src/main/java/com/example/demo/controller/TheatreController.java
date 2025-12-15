package com.example.demo.controller;

import com.example.demo.DTO.TheatreDTO;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.service.TheatreService;
import com.example.demo.service.UserService;
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
    private final UserService userService; // to fetch user from DB

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreDTO theatreDTO, Principal principal) {

        String userEmail= principal.getName();

        User loggedInUser = userService.getUserByEmail(userEmail);

        theatreService.addTheatre(theatreDTO,loggedInUser);

        return ResponseEntity.ok("New Theatre has been added successfully");
    }
}
