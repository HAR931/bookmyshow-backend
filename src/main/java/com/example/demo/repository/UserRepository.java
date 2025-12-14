package com.example.demo.repository;
import java.util.Optional;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findByEmail(String email);

    User findByPhone(String phone);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Integer findIdByMail(String email);



}
