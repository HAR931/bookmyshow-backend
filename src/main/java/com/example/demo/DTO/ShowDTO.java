package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class ShowDTO {

    @Column(nullable = false)
    @NotBlank
    private String movieName;

    @Column(nullable = false)
    @NotNull
    private LocalDate date;

    @Column(nullable = false)
    @NotNull
    private LocalTime startTime;

    @Column(nullable = false)
    @NotNull
    private LocalTime endTime;

    @Column(nullable = false)
    @NotNull
    private Integer seatCapacity;// total seats for this show
}
