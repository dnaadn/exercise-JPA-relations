package com.example.exercisejparelations.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {

    private Integer id;
    private String name;
    private Integer age;
    private String major;
}
