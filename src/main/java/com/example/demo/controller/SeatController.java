package com.example.demo.controller;


import com.example.demo.DTO.MyBookingResponse;
import com.example.demo.DTO.SeatBookingRequest;
import com.example.demo.DTO.SeatBookingResponse;
import com.example.demo.DTO.SeatResponse;
import com.example.demo.model.User;
import com.example.demo.service.SeatService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/bookSeats")
    public ResponseEntity<String> blockSeats(@RequestBody SeatBookingRequest request, Principal principal) {

        String email = principal.getName();
        User user=userService.getUserByEmail(email);
        Integer id=user.getId();

        String message =seatService.blockSeats(
                request.getShowId(),
                request.getSeatNumbers(),id,user);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping("/cancelSeats")
    public ResponseEntity<String> cancelSeats(
            @RequestBody SeatBookingRequest request,
            Principal principal
    ) {
        Integer userId = userService.findIdByMail(principal.getName());

        seatService.cancelSeats(
                request.getShowId(),
                request.getSeatNumbers(),
                userId
        );

        return ResponseEntity.ok("The seats you booked will be cancelled");
    }

    @GetMapping("/mybookings")
    public ResponseEntity<?>myBookings(Principal principal) {
        String email = principal.getName();
        Integer id = userService.findIdByMail(email);
        List<MyBookingResponse>myBookingResponses=seatService.getMyBookings(id);

        if(myBookingResponses.isEmpty()){
            return new ResponseEntity<>("No booking found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(myBookingResponses,HttpStatus.OK);
    }
}
