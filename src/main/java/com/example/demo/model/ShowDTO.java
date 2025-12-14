package com.example.demo.model;

import jakarta.persistence.Column;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
