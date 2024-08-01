package com.example.demo.student1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping 
    public List<Student> getStudents() {
        return studentService.getStudents();   
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
        ResponseMessage responseMessage = new ResponseMessage("Student added successfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        ResponseMessage responseMessage = new ResponseMessage("Student deleted successfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<ResponseMessage> updateStudent(@PathVariable("studentId") Long studentId,
                                                         @RequestBody Student updatedStudent) {
        studentService.updateStudent(studentId, updatedStudent);
        ResponseMessage responseMessage = new ResponseMessage("Student updated successfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
