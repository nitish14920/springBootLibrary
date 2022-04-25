package com.library.demo.controller;

import com.library.demo.book.Book;
import com.library.demo.book.BookRepository;
import com.library.demo.book.BookService;
import com.library.demo.book.BookServiceImpl;
import com.library.demo.student.Student;
import com.library.demo.student.StudentRepository;
import com.library.demo.student.StudentService;
import com.library.demo.student.StudentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BookControllerTest {

    private BookRepository bookRepository;


    private BookService bookService;

    @Test
    void findAll() {
        List<Book> books = new ArrayList<>();

        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);

        Book book1 = new Book("abc","cba");
        Book book2 = new Book("xyz","zyx");

        books.add(book1);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> bookList = bookService.findAll();

        assertThat(2 == bookList.size());

    }

    @Test
    void getStudentById(){
        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);

        when(bookRepository.findById(2)).thenReturn(Optional.of(new Book( "Rachel", "Jane")));

        Book book = bookService.findById(2);

        assertThat(book.getTitle().equals("Rachel"));
        assertThat(book.getAuthor().equals("Jane"));

    }

}
