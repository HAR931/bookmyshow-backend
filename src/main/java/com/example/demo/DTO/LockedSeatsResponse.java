package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockedSeatsResponse {
    private Integer id;
    private Integer seatNumber;
    private String status;
    private Integer lockedBy;
}
