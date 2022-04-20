package com.library.demo.student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);

    void deleteById(int id);

}
