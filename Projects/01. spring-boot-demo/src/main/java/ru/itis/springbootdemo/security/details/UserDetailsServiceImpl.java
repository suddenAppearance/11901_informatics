package ru.itis.springbootdemo.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.Optional;

/**
 * 01.03.2021
 * 08. spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userCandidate = usersRepository.findByEmail(email);
        if (userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
