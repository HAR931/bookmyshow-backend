package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovieSearchResponse {
    private String theatreName;
    private String location;
    private int screenNo;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer showId;

}
