package com.example.demo.DTO;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SeatResponse {
    private Integer id;
    private Integer seatNumber;
    private String status;
}
