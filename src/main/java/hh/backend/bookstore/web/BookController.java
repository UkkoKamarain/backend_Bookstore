package hh.backend.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.CategoryRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;

    // http://localhost:8080/login
    @GetMapping("/login")
    public String getLogin() {
        return "login"; // login.html
    }
    

    // http://localhost:8080/
    @GetMapping("/")
    public String getIndex(
        Model model
    ) {
        return "welcome"; // welcome.html
    }

    // http://localhost:8080/booklist
    @GetMapping("/booklist")
    public String getBookList(
        Model model
    ) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist"; // booklist.html
    }

    // http://localhost:8080/addbook 
    @GetMapping("/addbook")
    public String getAddBook(
        Model model
    ) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("book", new Book());
        return "addbook"; // addbook.html
    }

    @GetMapping("/editbook/{id}")
    public String getEditBook(
        @PathVariable("id") Long bookId,
        Model model
    ) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("editbook", bookRepository.findById(bookId));
        return "editbook"; // editbook.html
    }

    @RequestMapping("/savebook")
    public String reqSaveBook(
        @ModelAttribute Book book
    ) {
        bookRepository.save(book);
        return "redirect:/booklist"; // back to booklist
    }

    @RequestMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String reqDeleteBook(
        @PathVariable("id") Long bookId
    ) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist"; // stay on booklist
    }

}
