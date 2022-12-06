package com.example.lucampusstudentdata.controller;

import com.example.lucampusstudentdata.model.Student;
import com.example.lucampusstudentdata.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lucampus/student")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping()
    public List<Student> fetchStudentList() {
        return service.fetchStudentList();
    }

    @RequestMapping(value = "createStudent", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@Validated @RequestBody Student t) {
        service.saveStudentDetails(t);
    }

    @GetMapping("findStudent/{authId}")
    public ResponseEntity<Student> findStudent(@PathVariable("authId") String authId) {
        return new ResponseEntity<>(service.findStudent(authId), HttpStatus.OK);
    }
}
