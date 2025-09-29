package hh.backend.bookstore.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.backend.bookstore.domain.AppUser;
import hh.backend.bookstore.domain.AppUserRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final AppUserRepository userRepository;

    public UserDetailServiceImp(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentUser = userRepository.findByUsername(username);
        UserDetails user = new User(username, currentUser.getPassword(),
                AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }
}
