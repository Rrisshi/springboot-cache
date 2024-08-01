package com.example.demo;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.student1.ResponseMessage;
import com.example.demo.student1.Student;
import com.example.demo.student1.StudentService;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setName("Test Student");
        student.setEmail("test@student.com");
    }

    @Test
    public void testGetStudents() {
        List<Student> students = Arrays.asList(student);
        when(studentService.getStudents()).thenReturn(students);

        List<Student> result = studentController.getStudents();

        assertEquals(1, result.size());
        assertEquals("Test Student", result.get(0).getName());
        verify(studentService, times(1)).getStudents();
    }

    @Test
    public void testRegisterNewStudent() {
        ResponseEntity<ResponseMessage> response = studentController.registerNewStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Student added successfully", response.getBody().getMessage());
        verify(studentService, times(1)).addNewstudent(student);
    }

    @Test
    public void testDeleteStudent() {
        doNothing().when(studentService).deleteStudent(student.getId());

        studentController.deleteStudent(student.getId());

        verify(studentService, times(1)).deleteStudent(student.getId());
    }

    @Test
    public void testUpdateStudent() {
        Student updatedStudent = new Student();
        updatedStudent.setName("Updated Student");

        doNothing().when(studentService).updateStudent(eq(student.getId()), any(Student.class));

        ResponseEntity<ResponseMessage> response = studentController.updateStudent(student.getId(), updatedStudent);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student updated successfully", response.getBody().getMessage());
        verify(studentService, times(1)).updateStudent(eq(student.getId()), any(Student.class));
    }
}