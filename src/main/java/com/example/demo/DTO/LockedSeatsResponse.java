package com.example.demo.DTO;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LockedSeatsResponse {
    private Integer id;
    private Integer seatNumber;
    private String status;
    private Integer lockedBy;
}
