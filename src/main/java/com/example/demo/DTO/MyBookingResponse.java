package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyBookingResponse {
    private String theatreName;
    private String theatreLocation;
    private Integer screenNo;
    private String movieName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Integer> bookedSeats;
}
