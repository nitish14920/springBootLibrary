package com.library.demo.user;

import com.library.demo.student.Student;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteById(int id);
}
