package com.example.demo.DTO;


import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SeatBookingResponse {
    private Integer showId;
    private List<SeatResponse> bookedSeats;
    private List<SeatResponse> failedSeats;
    private String message;
}
