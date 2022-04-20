package com.library.demo.book;

import com.library.demo.exception.GameException;
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
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String listCourses(Model model)
    {
        List<Book> books = bookService.findAll();

        model.addAttribute("books", books);

        return "books/book-list";
    }

    @GetMapping("/showFormForAddBook")
    public String showFormForAdd(Model theModel) {

        theModel.addAttribute("book", new Book());

        return "books/book-form";
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("book") Book book, BindingResult theBindingResult, Model theModel)
    {
        try {
            if (theBindingResult.hasErrors()) {

                theModel.addAttribute("book", new Book());

                return "book-form";
            } else {
                bookService.save(book);

                return "redirect:/books/list";
            }
        }
        catch (Exception ex){
            throw new GameException("Book couldn't be added");
        }

    }

    @GetMapping("/showFormForUpdateBook")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel){

        Book book = bookService.findById(theId);

        theModel.addAttribute("book", book);

        return "books/book-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId){

        bookService.deleteById(theId);

        return "redirect:/books/list";

    }
}
