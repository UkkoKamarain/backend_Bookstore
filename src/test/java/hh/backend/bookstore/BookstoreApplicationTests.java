package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.bookstore.web.AppUserController;
import hh.backend.bookstore.web.BookController;
import hh.backend.bookstore.web.BookRestController;
import hh.backend.bookstore.web.CategoryController;

@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookController bController;
	@Autowired
	private CategoryController cController;
	@Autowired
	private AppUserController auController;
	@Autowired
	private BookRestController brController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bController).isNotNull();
		assertThat(cController).isNotNull();
		assertThat(auController).isNotNull();
		assertThat(brController).isNotNull();

	}

}
