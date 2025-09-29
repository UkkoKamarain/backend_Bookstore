package hh.backend.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@ResponseBody
@Controller
public class BookRestController {

    private BookRepository bookRepository;

    public BookRestController(BookRepository br) {
        this.bookRepository = br;
    }

    @GetMapping("/books")
    public List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> findBookRest(
        @PathVariable("id") Long bookId 
    ) {
        return bookRepository.findById(bookId);
    }

    @PostMapping("/books")
    public Book saveBookRest(
        @RequestBody Book book
    ) {
        return bookRepository.save(book);
    }
}
