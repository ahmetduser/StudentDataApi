package com.example.lucampusstudentdata.service;

import com.example.lucampusstudentdata.model.Student;
import com.example.lucampusstudentdata.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repo;

    @Override
    public void saveStudentDetails(Student student) {
        try {
            repo.saveStudentDetails(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> fetchStudentList() {
        return repo.fetchAllStudents();
    }

    @Override
    public Student findStudent(String authId) {
        return repo.findStudent(authId).get();
    }
}
