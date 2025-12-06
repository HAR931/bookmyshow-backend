package com.example.demo.service;

import com.example.demo.model.Screen;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final  TheatreService theatreService;
    private final ScreenRepository screenRepository;
    private final UserService userService;

    public void addScreen(String email,Integer theatreId, Screen screen){

        User user = userService.getUserByEmail(email);

        Theatre theatre=theatreService.getTheatreById(theatreId);

        if (!theatre.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("You are not the owner of this theatre");
        }

        screen.setTheatre(theatre);
        screenRepository.save(screen);
    }

}
