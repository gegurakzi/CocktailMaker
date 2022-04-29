package io.grz.cocktail.service;

import io.grz.cocktail.config.auth.PrincipalDetails;
import io.grz.cocktail.dto.UserAuthDTO;
import io.grz.cocktail.dto.UserDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User findByUsername(String username){
            return userRepository.findByUsername(username);
    }

    @Transactional
    public User register(UserAuthDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setRole("USER");
        user.setNickname(dto.getNickname());
        return userRepository.save(user);
    }

    @Transactional
    public UserDTO getUserDTOFromPrincipalName(String username){
        if(Objects.equals(username, "anonymousUser")) return null;
        User user = userRepository.findByUsername(username);
        if(user == null) return null;

        UserDTO userDTO = UserDTO.builder()
                .username(user.getUsername())
                .accessDate(user.getAccessDate())
                .cocktailRecipes(user.getCocktailRecipes())
                .nickname(user.getNickname())
                .ingredientRecipes(user.getIngredientRecipes())
                .postings(user.getPostings())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .replies(user.getReplies())
                .role(user.getRole())
                .userItems(user.getUserItems())
                .build();
        return userDTO;
    }

}
