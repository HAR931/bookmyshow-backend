package com.example.demo.DTO;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class MovieSearchResponse {
    private String theatreName;
    private String location;
    private Integer screenNo;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer showId;

}
