package io.grz.cocktail.service;

import io.grz.cocktail.dto.UserDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean isUsernameUnique(String username){
            User user = userRepository.findByUsername(username);
            if(user == null){
                return true;
            } else {
                return false;
            }
    }

    @Transactional
    public void register(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

}
