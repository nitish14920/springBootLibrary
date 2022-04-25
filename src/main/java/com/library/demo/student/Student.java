package com.library.demo.student;

import com.library.demo.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "* is required")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Enter valid name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "* is required")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Enter valid name")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "* is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Enter valid email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name = "students_books",
            joinColumns =  @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
