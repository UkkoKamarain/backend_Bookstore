package hh.backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreRunner(
			BookRepository bookRepository, 
			CategoryRepository categoryRepository
		) {
		return (args) -> {

			log.info("Save some sample categories");
			// adding some categories
			categoryRepository.save(new Category("fantasy"));
			categoryRepository.save(new Category("science"));
			categoryRepository.save(new Category("romance"));

			log.info("Insert testing books");

			// adding some test books
			bookRepository.save(new Book(
					"TestBook1",
					"TestAuthor1",
					2025,
					"TestIsbn1",
					2.50));
			bookRepository.save(new Book(
					"TestBook2",
					"TestAuthor1",
					2024,
					"TestIsbn2",
					300.33));

			log.info("Fetch all categories");
			for (Category c : categoryRepository.findAll()) {
				log.info(c.toString());
			}

			log.info("Fetch all books");
			for (Book b : bookRepository.findAll()) {
				log.info(b.toString());
			}
		};
	}
}
