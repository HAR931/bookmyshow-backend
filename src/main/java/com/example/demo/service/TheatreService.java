package com.example.demo.service;


import com.example.demo.DTO.TheatreDTO;
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



    public void addTheatre(TheatreDTO theatreDTO, User loggedInUser) {

        Theatre theatre = new Theatre();

        theatre.setName(theatreDTO.getName());
        theatre.setLocation(theatreDTO.getLocation());
        theatre.setOwner(loggedInUser);

        theatreRepository.save(theatre);
    }


}
