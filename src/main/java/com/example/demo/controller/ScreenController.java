package com.example.demo.controller;


import com.example.demo.DTO.ScreenDTO;
import com.example.demo.model.Screen;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.repository.ScreenRepository;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ScreenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        UserRepository userRepository;
        TheatreRepository theatreRepository;
        ScreenRepository screenRepository;

        @PostMapping("theatreId/{theatreId}/screens/add")
        public ResponseEntity<String> addScreen(
                @PathVariable Integer theatreId,
                @Valid @RequestBody ScreenDTO screenDTO,
                Principal principal) {

            String email = principal.getName();

            User user = userRepository.findByEmail(email);

            var theatre = theatreRepository.findById(theatreId);

            //check theatre exists
            if(theatre.isEmpty()) return new ResponseEntity<>("Theatre is not found", HttpStatus.NOT_FOUND);

            //check theatre belongs to loggedInUser
            if (!theatre.get().getOwner().getId().equals(user.getId())) {
                return new ResponseEntity<>("Your are not owner", HttpStatus.UNAUTHORIZED);
            }

            //check given screen number is not added
            boolean screenExists = screenRepository.existsByTheatreAndScreenNo(theatre.get(), screenDTO.getScreenNo());
            if (screenExists) {
                return new ResponseEntity<>("Screen already exists", HttpStatus.CONFLICT);
            }

            screenService.addScreen(email,theatre.get(), screenDTO);

            return new ResponseEntity<>("New Screen has been added",HttpStatus.OK);
        }
    }


