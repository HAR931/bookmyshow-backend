package com.example.demo.controller;

import com.example.demo.DTO.MovieSearchResponse;
import com.example.demo.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final ShowService showService;

    @GetMapping("/movieName")
    public List<MovieSearchResponse> searchShows(@RequestParam String movieName) {
        return showService.searchByMovieName(movieName);
    }
}
