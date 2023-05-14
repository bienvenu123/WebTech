package com.example.groupspring.service;



import com.example.law_farm.model.Lawyer;

import java.util.List;

public interface StudentService {
    // method signature
    // return value , method name;
    Lawyer registerStudent(Student stud);
    Lawyer updateStudent(Student stud);
    void deleteStudent(Long stud);
    List<Student> studentList();
    Lawyer findStudentByStudentId(Long stud);
}
