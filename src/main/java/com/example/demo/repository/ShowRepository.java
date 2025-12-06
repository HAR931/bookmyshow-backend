package com.example.demo.repository;

import com.example.demo.DTO.MovieSearchResponse;
import com.example.demo.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    @Query(""" 
                 SELECT s FROM Show s
                 WHERE s.screen.id = :screenId
                 AND s.date= :date
                 AND s.startTime < :newEnd
                 AND s.endTime > :newStart
            """)
    List<Show> findOverlappingShows(
            Integer screenId,
            LocalDate date,
            LocalTime newStart,
            LocalTime newEnd
            );

    @Query("""
    SELECT new com.example.demo.DTO.MovieSearchResponse(
        t.name,
        t.location,
        s.screenNo,
        sh.date,
        sh.startTime,
        sh.endTime,
        sh.id
    )
    FROM Show sh
    JOIN sh.screen s
    JOIN s.theatre t
    WHERE LOWER(sh.movieName) = LOWER(:movieName)
""")
    List<MovieSearchResponse> searchByMovieName(String movieName);

}
