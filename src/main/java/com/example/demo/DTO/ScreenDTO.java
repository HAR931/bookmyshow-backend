package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenDTO {
    @NotNull
    private Integer screenNo;
}
