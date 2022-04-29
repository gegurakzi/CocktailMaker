package io.grz.cocktail.config.auth;

import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Login attempt: username="+username);
        User user = userRepository.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException("user not found : username="+username);
        return new PrincipalDetails(user);
    }

}