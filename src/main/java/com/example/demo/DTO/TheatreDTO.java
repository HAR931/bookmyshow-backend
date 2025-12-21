package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TheatreDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String location;

}
