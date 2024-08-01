package com.example.demo.student1;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student Mariam = new Student( 
            "Mariam", 
            "mariam@gmail.com", 
            LocalDate.of(2002, 9,10)
			);
            
            Student alex = new Student( 
            "alex", 
            "alex@gmail.com", 
            LocalDate.of(2005, 9,10)
			);

            repository.saveAll(
                List.of(Mariam,alex)
            );
            
            
        };
    };
};


