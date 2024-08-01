package com.example.demo.student1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, Student updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setDob(updatedStudent.getDob());

        studentRepository.save(student);
    }
}
