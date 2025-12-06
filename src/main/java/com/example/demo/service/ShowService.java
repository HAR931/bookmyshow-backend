package com.example.demo.service;

import com.example.demo.DTO.MovieSearchResponse;
import com.example.demo.model.*;
import com.example.demo.repository.ScreenRepository;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ShowService {

    private final UserService userService;
    private final ScreenRepository screenRepository;
    private  final ShowRepository showRepository;
    private final SeatService seatService;


    public void addShow(Integer theatreId, Integer screenId, Show show,String email){

        User user = userService.getUserByEmail(email);

        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        Theatre theatre = screen.getTheatre();  // Now works

        if (!theatre.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this theatre");
        }

        List<Show> overlap = showRepository.findOverlappingShows(
                screenId,
                show.getDate(),
                show.getStartTime(),
                show.getEndTime()
        );

        if (!overlap.isEmpty()) {
            throw new RuntimeException("This show time overlaps with another show!");
        }

        Integer capacity=show.getSeatCapacity();

        show.setScreen(screen);
        showRepository.save(show);

        seatService.createSeats(capacity,show);

    }
    public List<MovieSearchResponse>searchByMovieName(String movieName) {
        return showRepository.searchByMovieName(movieName);
    }


}
