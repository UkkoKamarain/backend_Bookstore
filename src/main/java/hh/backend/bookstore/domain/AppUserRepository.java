package hh.backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
