package com.example.demo.controller;

import com.example.demo.DTO.MovieSearchResponse;
import com.example.demo.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController{

    private final ShowService showService;

    @GetMapping("/movie/search")
    public ResponseEntity<?>searchShows(@RequestParam String movieName) {

        List <MovieSearchResponse>movieSearchResponses=showService.searchByMovieName(movieName);

        if(movieSearchResponses.isEmpty())
            return new ResponseEntity<>(movieName+" is not streamed at any theatre", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(movieSearchResponses);
    }
}
