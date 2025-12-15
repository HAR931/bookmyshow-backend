package com.example.demo.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
