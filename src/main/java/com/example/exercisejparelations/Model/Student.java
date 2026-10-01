package com.example.exercisejparelations.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotNull(message = "age is required")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "major is required")
    @Column(columnDefinition = "varchar(50) not null")
    private String major;


    @ManyToMany
    @JsonIgnore
    private Set<Course> courseSet;
}
