package com.example.demo.student1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long>{

    Object save = null;

    @Query 
    ("SELECT s FROM Student s WHERE s.email =?1")
    Optional<Student> findStudentByEmail(String email);
}
// package com.example.demo.student1;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface StudentRepository extends JpaRepository<Student, Long> {

//     public Optional<Student> findStudentByEmail(String email);
//     // Your custom query methods, if any
// }