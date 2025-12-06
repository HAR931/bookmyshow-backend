package com.example.demo.controller;

import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.service.TheatreService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;
    private final UserService userService; // to fetch user from DB

    @PostMapping("/add")
    public Theatre addTheatre(@RequestBody Theatre theatre) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userEmail = auth.getName();

        User loggedInUser = userService.getUserByEmail(userEmail);

        return theatreService.addTheatre(theatre, loggedInUser);
    }
}
