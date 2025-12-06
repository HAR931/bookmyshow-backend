package com.example.demo.controller;


import com.example.demo.model.Screen;
import com.example.demo.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PostMapping("/{theatreId}/screens")
    public String addScreen(@PathVariable Integer theatreId, @RequestBody Screen screen,Principal principal){
        String email=principal.getName();
        screenService.addScreen(email,theatreId, screen);
        return "new Screen has been added successfully";
    }
}
