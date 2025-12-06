package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatBookingResponse {
    private Integer showId;
    private List<SeatResponse> bookedSeats;
    private List<SeatResponse> failedSeats;
    private String message;
}
