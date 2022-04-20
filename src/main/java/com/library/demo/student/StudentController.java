package com.library.demo.student;

import com.library.demo.book.Book;
import com.library.demo.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    private BookService bookService;

    @Autowired
    public StudentController(StudentService studentService, BookService bookService) {
        this.studentService = studentService;
        this.bookService = bookService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String findAll(Model theModel){

        List<Student> students = studentService.findAll();

        theModel.addAttribute("students", students);

        return "students/student-list";
    }

    @GetMapping("/showFormForAddStudent")
    public String showFormForAdd(Model theModel){

        theModel.addAttribute("student",new Student());

        return "students/student-form";
    }

    @PostMapping("/save")
    public String savePlayer(@Valid @ModelAttribute("student") Student student){

        studentService.save(student);

        return "redirect:/students/list";
    }

    @GetMapping("/showFormForUpdateStudent")
    public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel){

        Student student = studentService.findById(id);

        theModel.addAttribute("student", student);

        List<Book> books = bookService.findAll();

        theModel.addAttribute("books", books);

        return "students/student-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("playerId") int id){

        studentService.deleteById(id);

        return "redirect:/students/list";
    }
    @GetMapping("/showFormToEnroll")
    public String showFormToEnroll(Model theModel){

        theModel.addAttribute("student",new Student());

        List<Book> books = bookService.findAll();

        theModel.addAttribute("books", books);

        return "students/enroll";
    }

    @PostMapping("/saveBooks")
    public String saveCourse(@Valid @ModelAttribute("student") Student student, BindingResult theBindingResult, Model theModel){

        if(theBindingResult.hasErrors()){

            theModel.addAttribute("student", student);
            List<Book> books = bookService.findAll();
            theModel.addAttribute("books", books);

            return "students/enroll";
        }
        else {
            studentService.save(student);

            return "students/student-list";
        }
    }
}
