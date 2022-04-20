package com.library.demo.controller;

import com.library.demo.student.Student;
import com.library.demo.student.StudentRepository;
import com.library.demo.student.StudentService;
import com.library.demo.student.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    private StudentRepository studentRepository;


    private StudentService studentService;

    @Test
    void findAll() {
        List<Student> students = new ArrayList<>();

        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        Student student1 = new Student("Nitish","Kumar","ni@gmail.com");
        Student student2 = new Student("hitesth","ku","hiku@gmail.com");

        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> studentList = studentService.findAll();

        assertThat(2 == studentList.size());

    }

    @Test
    void getStudentById(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        when(studentRepository.findById(2)).thenReturn(Optional.of(new Student( "Rachel", "Jane", "rachel@gmail.com")));

        Student student = studentService.findById(2);

        assertThat(student.getFirstName().equals("Rachel"));
        assertThat(student.getLastName().equals("Jane"));
        assertThat(student.getEmail().equals("rachel@gmail.com"));

    }

    @Test
    void updateStudent(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        Student student = new Student("x","y","xyz@gmail.com");

        when(studentRepository.save(student)).thenReturn(student);

        Student expectedStudent = studentService.save(student);

        assertThat(expectedStudent).isNotNull();
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void deleteStudent(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        int id = 3;

        studentService.deleteById(id);

        verify(studentRepository,times(1)).deleteById(id);
    }
}
