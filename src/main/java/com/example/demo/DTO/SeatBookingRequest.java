package com.example.demo.DTO;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SeatBookingRequest {
    private Integer showId;
    private List<Integer> seatNumbers;

    // getters and setters
}
