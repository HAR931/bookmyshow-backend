package com.example.demo.repository;

import com.example.demo.model.Screen;
import com.example.demo.model.User;
import com.example.demo.service.TheatreService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
