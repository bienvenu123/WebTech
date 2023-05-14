package com.example.bibafrica.controller;

import com.example.bibafrica.model.Lawyer;

import com.example.bibafrica.services.DatabasePDFService;
import com.example.bibafrica.services.LawyerInterface;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class LawController {
    @Autowired
    LawyerInterface  studentService;

    @GetMapping("/home")
    public String homePage(Model model){

        return findPaginated(1,model);
    }

    @GetMapping("/inde")
    public String ind(){
        return "homes";
    }
    @GetMapping("/")
    public String homme()
    { return "aboutus";}
    @GetMapping("/come")
    public String homes()
    { return "aboutus";}
    @GetMapping("/lawyer")
    public String candidate(){
        return "package";
    }
    @GetMapping("/tem")
    public String temp(){
        return "templa";
    }
    @GetMapping("/registration")
    public String registerStudentPage(Model model){
        Lawyer stud = new Lawyer();
        model.addAttribute("student", stud);
        return "registrar";
    }
    @GetMapping("/student-page")
    public String studentpage(Model model){
        Lawyer stud = new Lawyer();
        model.addAttribute("student", stud);
        return "student";
    }

    @PostMapping("/register")
    public String registerLawyer(@ModelAttribute("student") Lawyer theStudent){
        Lawyer savedStudent = studentService.registerStudent(theStudent);
        if(savedStudent != null){
            return "redirect:/student-page?success";
        }else {
            return "redirect:/student-page?error";
        }
    }

    @PostMapping("/reg")
    public String registerStudentInDb(@ModelAttribute("student") Lawyer theStudent){
        Lawyer savedStudent = studentService.registerStudent(theStudent);
        if(savedStudent != null){
            return "redirect:/registration?success";
        }else {
            return "redirect:/registration?error";
        }
    }

    @GetMapping("/home/update/{id}")
    public String editStudent(@PathVariable String id, Model model){

        Long  lawyerId=Long.parseLong(id);
        model.addAttribute("student", studentService.findStudentByStudentId(lawyerId));
        return "update";
    }
    @PostMapping("/home/{id}")
    public String updateStudent(@PathVariable String id,
                                @ModelAttribute("student") Lawyer student, Model model)
    {

        Long  lawyerId=Long.parseLong(id);
        Lawyer existingStudent=studentService.findStudentByStudentId(lawyerId);
        existingStudent.setTel(student.getTel());
        existingStudent.setId(student.getId());
        existingStudent.setNames(student.getNames());
        existingStudent.setCases(student.getCases());
        existingStudent.setDate(student.getDate());
        existingStudent.setEmails(student.getEmails());
        existingStudent.setLawyer(student.getLawyer());
        studentService.updateStudent(existingStudent);
        return "redirect:/home";
    }
    @GetMapping("/home/{id}")
    public String deleteStudent(@PathVariable String id)
    {
    Long  lawyerId=Long.parseLong(id);
        studentService.deleteStudent(lawyerId);
        return "redirect:/home";
    }
    @GetMapping("/sear")
    public String search(Model model)
    {
        Lawyer student=new Lawyer();
        model.addAttribute("search",student);

        return "search";
    }
    @PostMapping("/sear")
    public String searchh(@ModelAttribute("search") Lawyer student, Model model)
    {
        Lawyer student1=studentService.findStudentByStudentId(student.getId());
        if (student1!=null){
            model.addAttribute("student1",student1);
            return "search";
        }
        else {
            model.addAttribute("error","this person is not exist");
            return "search";
        }
    }

    @GetMapping("/exportCsv")
    public void exportCSV(HttpServletResponse response)
            throws Exception {

        //set file name and content type
        String filename = "Volunteer-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
        StatefulBeanToCsv<Lawyer> writer = new StatefulBeanToCsvBuilder<Lawyer>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
                .build();
        //write all employees data to csv file
        writer.write(studentService.studentList());

    }

    @GetMapping(value = "/exportPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> volunteerReport()  throws IOException {
        List<Lawyer> volunteers = (List<Lawyer>) studentService.studentList();

        ByteArrayInputStream bis = DatabasePDFService.employeePDFReport(volunteers);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=VolunteerReport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize=5;
        Page<Lawyer> page=studentService.pagenateStudent(pageNo,pageSize);
        List<Lawyer> studentList=page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listStudents",studentList);
        return "home-page";

    }

}
