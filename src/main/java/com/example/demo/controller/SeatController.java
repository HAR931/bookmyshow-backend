package com.example.demo.controller;


import com.example.demo.DTO.MyBookingResponse;
import com.example.demo.DTO.SeatBookingRequest;
import com.example.demo.DTO.SeatBookingResponse;
import com.example.demo.DTO.SeatResponse;
import com.example.demo.model.User;
import com.example.demo.service.SeatService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;
    private final UserService userService;

    @GetMapping("/seats/{showId}")
    public List<SeatResponse> getSeats(@PathVariable Integer showId) {
        return seatService.getSeatsByShowId(showId);
    }

    @PostMapping("/block")
    public String blockSeats(@RequestBody SeatBookingRequest request, Principal principal) {

        String email = principal.getName();
        Integer id = userService.findIdByMail(email);
        User user=userService.getUserByEmail(email);

        // Call service and return the response
        return seatService.blockSeats(
                request.getShowId(),
                request.getSeatNumbers(),id,user);
    }

    @PostMapping("/confirmSeats")
    public String conformSeats(@RequestBody SeatBookingRequest request, Principal principal) {

        String email = principal.getName();
        User user=userService.getUserByEmail(email);

        // Call service and return the response
        return seatService.confrimSeats(
                request.getShowId(),
                request.getSeatNumbers(),user);
    }
    @PostMapping("/cancelSeats")
    public String cancelSeats(@RequestBody SeatBookingRequest request, Principal principal) {

        String email = principal.getName();
        Integer id = userService.findIdByMail(email);

        // Call service and return the response
        return seatService.cancelSeats(
                request.getShowId(),
                request.getSeatNumbers(),id);
    }
    @GetMapping("/mybookings")
    public List<MyBookingResponse> myBookings(Principal principal) {
        String email = principal.getName();
        Integer id = userService.findIdByMail(email);
        return seatService.getMyBookings(id);
    }

}
