package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.backend.bookstore.domain.AppUser;
import hh.backend.bookstore.domain.AppUserRepository;
import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookstoreJPArepoTests {
    @Autowired
    private BookRepository bRepo;
    @Autowired
    private CategoryRepository cRepo;
    @Autowired
    private AppUserRepository auRepo;
    private Book book = new Book("Dogs day off", "Little old me", 2003, "10294709", 15.0, null);
    private Category category = new Category("Sci-Fi");
    private AppUser appUser = new AppUser("tester", "tester", "tester@testing.fi", "USER");

    //  Book related tests
    //  B   Create
    @Test
    public void BookRepositoryCreate() {
        bRepo.save(book);
        assertThat(bRepo.findById(book.getId())).isNotEmpty();
    }

    //  B   Delete
    @Test
    public void BookRepositoryDelete() {
        bRepo.save(book);
        bRepo.deleteById(book.getId());
        assertThat(bRepo.findById(book.getId())).isEmpty();
    }
    
    //  B   Search
    @Test
    public void BookRepositorySearchByTitle() {
        List<Book> testBooks = bRepo.findByTitle("TestBook1");
        assertThat(testBooks).hasSize(1);
        assertThat(testBooks.get(0).getPrice()).isEqualTo(2.50); 
    }

    //  Category related tests
    //      Create
    @Test
    public void CategoryRepositoryCreate() {
        cRepo.save(category);
        assertThat(cRepo.findById(category.getCategoryid())).isNotEmpty();
        
    }

    //  C   Delete
    @Test
    public void CategoryRepositoryDelete() {
        cRepo.save(category);
        cRepo.delete(category);
        assertThat(cRepo.findById(category.getCategoryid())).isEmpty();
    }
    
    //  C   Search
    @Test
    public void CategoryRepositorySearchByName() {
        List<Category> testCategories = cRepo.findByName("Fantasy");

        assertThat(testCategories).hasSize(1);
        assertThat(testCategories.get(0).getCategoryid()).isEqualTo(2);
    }

    //  AppUser related tests
    //  AU  Create
    @Test
    public void AppUserRepositoryCreate() {
        auRepo.save(appUser);
        assertThat(auRepo.findById(appUser.getUsername())).isNotEmpty();
    }

    //  AU  Delete
    @Test
    public void AppUserRepositoryDelete() {
        auRepo.save(appUser);
        auRepo.delete(appUser);
        assertThat(auRepo.findById(appUser.getUsername())).isEmpty();
    }
    
    //  AU  Search
    @Test
    public void AppUserRepositorySearchByUsername() {
        AppUser testUser = auRepo.findByUsername("admin");

        assertThat(testUser.getRole()).isEqualTo("ADMIN");
    }
}
