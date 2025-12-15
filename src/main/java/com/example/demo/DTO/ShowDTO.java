package com.example.demo.DTO;

import jakarta.persistence.Column;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class ShowDTO {

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer seatCapacity;// total seats for this show
}
