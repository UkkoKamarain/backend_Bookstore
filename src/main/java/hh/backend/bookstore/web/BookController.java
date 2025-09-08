package hh.backend.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/index")
    public String getIndex(
        Model model
    ) {
        return "welcome"; // welcome.html
    }

    @GetMapping("/booklist")
    public String getBookList(
        Model model
    ) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist"; // booklist.html
    }
}
