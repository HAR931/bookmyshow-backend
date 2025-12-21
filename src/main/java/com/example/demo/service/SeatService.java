package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.model.Seat;
import com.example.demo.model.Show;
import com.example.demo.model.User;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {


    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    public void createSeats(int capacity, Show show) {

        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= capacity; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setShow(show);
            seats.add(seat);
        }

        seatRepository.saveAll(seats);
    }
    public List<SeatResponse> getSeatsByShowId(Integer showId) {
        List<SeatResponse> seats = seatRepository. findSeatsByShowId(showId);
        return seats;
    }

    @Transactional
    public String blockSeats(Integer showId,
                                          List<Integer> seatNumbers,
                                          Integer id,User user) {

        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(5);

        if(seatRepository.lockSeats(showId, seatNumbers,expiryTime,id)==seatNumbers.size()){
            return confirmSeats(showId,seatNumbers,user,id);
        };

        List<LockedSeatsResponse> statuses =
                seatRepository.getSeatStatuses(showId,seatNumbers);

        List<LockedSeatsResponse> failed = new ArrayList<>();

        for (LockedSeatsResponse s : statuses) {
            if ((s.getStatus().equals("LOCKED")&&!s.getLockedBy().equals(id))||s.getStatus().equals("BOOKED"))
                failed.add(s);
        }

        StringBuilder s = new StringBuilder("Seat Numbers : ");

            for (int i = 0; i < failed.size(); i++) {
                s.append(failed.get(i).getSeatNumber());
                if (i < failed.size() - 1) {
                    s.append(",");
                }
            }

            s.append(" Already booked");

            return s.toString();
    }


    public String confirmSeats(Integer showId,List<Integer>seatNumbers,User user,Integer userId){
        seatRepository.confirmSeats(showId,seatNumbers,user,userId);
        return "Seat are booked successfully";
    }

   @Transactional
    public void cancelSeats(Integer showId, List<Integer> seats, Integer userId) {
        seatRepository.unlockSeats(showId, seats, userId);
    }

    public List<MyBookingResponse> getMyBookings(Integer userId) {

        List<Object[]> rows = seatRepository.findMyBookingsNative(userId);

        List<MyBookingResponse> result = new ArrayList<>();

        for (Object[] row : rows) {

            String theatreName = (String) row[0];
            String location = (String) row[1];
            Integer screenNo = ((Number) row[2]).intValue();
            String movieName = (String) row[3];
            LocalDate date = LocalDate.parse(row[4].toString());
            LocalTime startTime = LocalTime.parse(row[5].toString());
            LocalTime endTime = LocalTime.parse(row[6].toString());

            String seatsStr = (String) row[7];
            String[] seatArr = seatsStr.split(",");

            List<Integer> seatNumbers = new ArrayList<>();

            for (String seat : seatArr) {
                seatNumbers.add(Integer.parseInt(seat));
            }

            MyBookingResponse response = new MyBookingResponse(
                    theatreName,
                    location,
                    screenNo,
                    movieName,
                    date,
                    startTime,
                    endTime,
                    seatNumbers
            );

            result.add(response);
        }

        return result;
    }
}

