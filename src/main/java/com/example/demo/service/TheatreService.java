package com.example.demo.service;


import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor  // Lombok generates a constructor for final fields
public class TheatreService {

    private final TheatreRepository theatreRepository; // final -> constructor generated automatically

    public Theatre addTheatre(Theatre theatre, User loggedInUser) {
        theatre.setOwner(loggedInUser);
        return theatreRepository.save(theatre);
    }
    public Theatre getTheatreById(Integer theatreId) {
        return theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

}
