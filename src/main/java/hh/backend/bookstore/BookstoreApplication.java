package hh.backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import hh.backend.bookstore.domain.*;

@SpringBootApplication
public class BookstoreApplication {

    private final AppUserRepository appUserRepository;
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    BookstoreApplication(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreRunner(
			BookRepository bookRepository, 
			CategoryRepository categoryRepository
		) {
		// Creating categories for easier inserting
		Category noCat = new Category("No category");
		Category fantasyCat = new Category("Fantasy");
		Category scienceCat = new Category("Science");
		Category romanceCat = new Category("Romance");
		AppUser user1 = new AppUser("user", "$2a$10$IiyD/u5KKXV6EVyZKETumONM7p5hqQvRqcfNNj/Z5513OS2E.ZCoC", "user@bookstore.fi", "USER");
		AppUser user2 = new AppUser("admin", "$2a$10$1PKicp/C8HewhB8Ga/J./OjMz0A132x7GhfBcClyYOXAmdVW1HTQy", "admin@bookstore.fi", "ADMIN");

		return (args) -> {

			log.info("Insert users");
			appUserRepository.save(user1);
			appUserRepository.save(user2);

			log.info("Save some sample categories");
			// adding sample categories
			categoryRepository.save(noCat);
			categoryRepository.save(fantasyCat);
			categoryRepository.save(scienceCat);
			categoryRepository.save(romanceCat);

			log.info("Insert testing books");

			// adding some test books
			bookRepository.save(new Book(
					"TestBook1",
					"TestAuthor1",
					2025,
					"TestIsbn1",
					2.50,
					fantasyCat));
			bookRepository.save(new Book(
					"TestBook2",
					"TestAuthor1",
					2024,
					"TestIsbn2",
					300.33,
					romanceCat));

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
