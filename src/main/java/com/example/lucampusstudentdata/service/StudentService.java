package com.example.lucampusstudentdata.service;


import com.example.lucampusstudentdata.model.Student;

import java.util.List;

public interface StudentService {

    void saveStudentDetails(Student student);

    List<Student> fetchStudentList();

    Student findStudent(String id);

}
