package com.example.demo.service;

import com.example.demo.DTO.ScreenDTO;
import com.example.demo.model.Screen;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.repository.ScreenRepository;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScreenService {

        private final TheatreRepository theatreRepository;
        private final ScreenRepository screenRepository;
        private final UserRepository userRepository;


        public void addScreen(String email, Theatre theatre, ScreenDTO screenDTO) {

            Screen screen = new Screen();
            screen.setScreenNo(screenDTO.getScreenNo());
            screen.setTheatre(theatre);

            screenRepository.save(screen);

        }
    }





