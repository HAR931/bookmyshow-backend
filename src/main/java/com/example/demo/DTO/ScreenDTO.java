package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDTO {
    @NotBlank
    private Integer screenNo;
}
