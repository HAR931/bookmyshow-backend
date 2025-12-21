package com.example.demo.service;

import com.example.demo.DTO.MovieSearchResponse;
import com.example.demo.DTO.ShowDTO;
import com.example.demo.model.*;
import com.example.demo.repository.ScreenRepository;
import com.example.demo.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ShowService {

    private final UserService userService;
    private final ScreenRepository screenRepository;
    private  final ShowRepository showRepository;
    private final SeatService seatService;


    public ResponseEntity<String> addShow(Integer screenId, ShowDTO showDTO, String email){


        User user = userService.getUserByEmail(email);

        var screen = screenRepository.findById(screenId);

        if(screen.isEmpty()){
            return new ResponseEntity<>("Screen not found",HttpStatus.NOT_FOUND);
        }

        Integer ownerId=screenRepository.getOwnerId(screenId);

        if(!user.getId().equals(ownerId))return new ResponseEntity<>("Your are not the owner of the screen",HttpStatus.UNAUTHORIZED);

        List<Show> overlap = showRepository.findOverlappingShows(
                screenId,
                showDTO.getDate(),
                showDTO.getStartTime(),
                showDTO.getEndTime()
        );

        if (!overlap.isEmpty()) {
            return new ResponseEntity<>("A show is already scheduled for this screen during the selected time",HttpStatus.CONFLICT);
        }

        Integer capacity=showDTO.getSeatCapacity();

        Show show=new Show();

        show.setMovieName(showDTO.getMovieName());
        show.setDate(showDTO.getDate());
        show.setStartTime(showDTO.getStartTime());
        show.setEndTime(showDTO.getEndTime());
        show.setSeatCapacity(showDTO.getSeatCapacity());
        show.setScreen(screen.get());

        showRepository.save(show);

        seatService.createSeats(capacity,show);

        return ResponseEntity.ok("New Show has been successfully added");

    }
    public List<MovieSearchResponse>searchByMovieName(String movieName) {
        return showRepository.searchByMovieName(movieName);
    }

}
