package com.example.demo.DTO;

import jakarta.transaction.Transactional;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class SeatBookingRequest {
    private Integer showId;
    private List<Integer> seatNumbers;

    // getters and setters
}
