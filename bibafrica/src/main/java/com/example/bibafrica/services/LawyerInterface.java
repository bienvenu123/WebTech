package com.example.bibafrica.services;

import com.example.bibafrica.model.Lawyer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LawyerInterface {
    Lawyer registerStudent(Lawyer stud);
    Lawyer updateStudent(Lawyer stud);
    void deleteStudent(Long stud);
    List<Lawyer> studentList();
    Lawyer findStudentByStudentId(Long stud);

    Page<Lawyer> pagenateStudent(int pageNo, int pageSize);

}
