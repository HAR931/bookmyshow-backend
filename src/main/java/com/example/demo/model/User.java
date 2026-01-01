package com.example.demo.model;


import com.example.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theatre> theatres;

    @OneToMany(mappedBy = "bookedBy",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat>seat;
}

