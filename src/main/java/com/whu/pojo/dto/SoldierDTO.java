package com.whu.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldierDTO {
    private String name;
    private Integer gender;
    private String birthPlace;
    private String resettlement;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String img;
    private String militaryPositions;
    private String experience;
}
