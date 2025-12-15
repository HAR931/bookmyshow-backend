package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenDTO {
    @NotBlank
    private Integer screenNo;
}
