package com.example.demo.repository;

import com.example.demo.DTO.LockedSeatsResponse;
import com.example.demo.DTO.SeatResponse;
import com.example.demo.model.Seat;
import com.example.demo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("""
    SELECT new com.example.demo.DTO.SeatResponse(
        s.id,
        s.seatNumber,
        s.status
    )
    FROM Seat s
    WHERE s.show.id = :showId
""")
    List<SeatResponse> findSeatsByShowId( Integer showId);


    @Modifying
    @Transactional
    @Query("""
    UPDATE Seat s
    SET s.status = 'LOCKED', s.lockedBy = :id
    WHERE s.show.id = :showId
      AND s.seatNumber IN :seatNumbers
      AND (s.status = 'AVAILABLE' OR
           (s.status = 'LOCKED' AND s.lockedBy = :id))
""")
    int lockSeats(Integer showId, List<Integer> seatNumbers,
                  Integer id);

    @Query("""
    SELECT new com.example.demo.DTO.LockedSeatsResponse(
        s.id, s.seatNumber, s.status,s.lockedBy
    )
    FROM Seat s
    WHERE s.show.id = :showId
    AND s.seatNumber IN :seatNumbers
""")
    List<LockedSeatsResponse> getSeatStatuses(Integer showId,
                                              List<Integer> seatNumbers);

    @Transactional
    @Modifying
    @Query("""
    UPDATE Seat s
    SET s.status = 'BOOKED', s.bookedBy =:user
    WHERE s.show.id = :showId
    AND s.seatNumber IN :seatNumbers
""")
    public int confrimSeats(Integer showId, List<Integer> seatNumbers,User user);

    @Transactional
    @Modifying
    @Query("""
    UPDATE Seat s
    SET s.status = 'AVAILABLE', s.lockedBy = NULL,s.bookedBy=NULL
    WHERE s.show.id = :showId
    AND s.lockedBy = :userId
    AND s.seatNumber IN :seatNumbers
""")
    int unlockSeats(Integer showId,List<Integer> seatNumbers ,Integer userId);

    @Query(value = """
    SELECT
        t.name AS theatreName,
        t.location AS theatreLocation,
        sc.screen_no AS screenNo,
        sh.movie_name AS movieName,
        sh.date AS date,
        sh.start_time AS startTime,
        sh.end_time AS endTime,
        GROUP_CONCAT(s.seat_number) AS seatNumbers
    FROM seat s
    JOIN shows sh ON s.show_id = sh.id
    JOIN screen sc ON sh.screen_id = sc.id
    JOIN theatre t ON sc.theatre_id = t.id
    WHERE s.user_id = :userId
    GROUP BY sh.id;
""", nativeQuery = true)
    List<Object[]> findMyBookingsNative(Integer userId);
}

