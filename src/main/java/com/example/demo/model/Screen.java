package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "screen", uniqueConstraints = {@UniqueConstraint(columnNames = {"screen_no", "theatre_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer screenNo;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows;


}




