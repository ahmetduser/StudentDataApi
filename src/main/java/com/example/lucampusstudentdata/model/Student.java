package com.example.lucampusstudentdata.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private int id;
    private String studentAuthId;
    private String name;
    private String surname;
    private String email;
    private String major;
    private String studentId;
}
