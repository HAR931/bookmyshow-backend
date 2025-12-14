package com.example.demo.repository;

import com.example.demo.model.Screen;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.service.TheatreService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

    boolean existsByTheatreAndScreenNo(Theatre theatre, Integer screenNo);

    @Query("""
    SELECT t.owner.id
    FROM Screen s
    JOIN s.theatre t
    WHERE s.id = :screenId
""")
    Integer getOwnerId(Integer screenId);


}
