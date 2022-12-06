package com.example.lucampusstudentdata.repository;


import com.example.lucampusstudentdata.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private Connection con = null;
    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;


    private Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }

    public void saveStudentDetails(Student student) throws SQLException {
        String create = "INSERT INTO student (studentAuthId, name, surname, email, major, studentId) " +
                " VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stm = getDBConnection().prepareStatement(create);


        stm.setString(1, student.getStudentAuthId());
        stm.setString(2, student.getName());
        stm.setString(3, student.getSurname());
        stm.setString(4, student.getEmail());
        stm.setString(5, student.getMajor());
        stm.setString(6, student.getStudentId());
        stm.executeUpdate();
    }

    public List<Student> fetchAllStudents() {
        List<Student> studentList = new ArrayList<>();
        Student student;
        String selectAll = "SELECT * FROM student";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentAuthId(rs.getString("studentAuthId"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setEmail(rs.getString("email"));
                student.setMajor(rs.getString("major"));
                student.setStudentId(rs.getString("studentId"));
                studentList.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    public Optional<Student> findStudent(String id) {
        Optional<Student> su = Optional.empty();
        String find = "SELECT * FROM student WHERE studentAuthId = '" + id + "'";
        Student student = new Student();

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(find);

            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setStudentAuthId(rs.getString("studentAuthId"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setEmail(rs.getString("email"));
                student.setMajor(rs.getString("major"));
                student.setStudentId(rs.getString("studentId"));

                su = Optional.of(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return su;
    }
}
