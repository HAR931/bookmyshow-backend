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


        public ResponseEntity<String> addScreen(String email, Integer theatreId, ScreenDTO screenDTO) {

            User user = userRepository.findByEmail(email);

            var theatre = theatreRepository.findById(theatreId);

            if(theatre.isEmpty()) return new ResponseEntity<>("Theatre is not found", HttpStatus.NOT_FOUND);

            if (!theatre.get().getOwner().getId().equals(user.getId())) {
                return new ResponseEntity<>("Your are not owner", HttpStatus.UNAUTHORIZED);
            }

            boolean screenExists = screenRepository.existsByTheatreAndScreenNo(theatre.get(), screenDTO.getScreenNo());
            if (screenExists) {
                return new ResponseEntity<>("Screen already exists", HttpStatus.CONFLICT);
            }

            Screen screen = new Screen();
            screen.setScreenNo(screenDTO.getScreenNo());
            screen.setTheatre(theatre.get());
            screenRepository.save(screen);

            return new ResponseEntity<>("New screen has been successfully added", HttpStatus.CREATED);
        }
    }





