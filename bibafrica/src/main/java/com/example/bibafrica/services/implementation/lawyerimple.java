package com.example.bibafrica.services.implementation;

import com.example.bibafrica.model.Lawyer;
import com.example.bibafrica.repository.LawyerRepository;
import com.example.bibafrica.services.LawyerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class lawyerimple implements LawyerInterface {
    @Autowired
    LawyerRepository studentRepository;

    @Override
    public Lawyer registerStudent(Lawyer stud) {
        return studentRepository.save(stud);
    }

    @Override
    public Lawyer updateStudent(Lawyer stud) {
        return studentRepository.save(stud);
    }

    @Override
    public void deleteStudent(Long stud) {
        studentRepository.deleteById(stud);
    }


    @Override
    public List<Lawyer> studentList() {
        return studentRepository.findAll();
    }

    @Override
    public Lawyer findStudentByStudentId(Long stud) {
        return studentRepository.findById(stud).get();
    }

    @Override
    public Page<Lawyer> pagenateStudent(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo -1,pageSize);
        return this.studentRepository.findAll(pageable);
    }

}
