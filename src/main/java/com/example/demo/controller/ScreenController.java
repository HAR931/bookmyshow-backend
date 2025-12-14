package com.example.demo.controller;


import com.example.demo.DTO.ScreenDTO;
import com.example.demo.model.Screen;
import com.example.demo.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

        @PostMapping("theatreId/{theatreId}/screens/add")
        public ResponseEntity<String> addScreen(
                @PathVariable Integer theatreId,
                @RequestBody ScreenDTO screenDTO,
                Principal principal) {

            String email = principal.getName();
            return screenService.addScreen(email, theatreId, screenDTO);
        }
    }


