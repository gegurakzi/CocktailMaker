package io.grz.cocktail.dto;

import io.grz.cocktail.model.JoinTable.UserIngredient;
import io.grz.cocktail.model.article.Posting.Posting;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.IngredientRecipe;
import io.grz.cocktail.model.article.Reply.Reply;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private long id;
    private String username;
    private String password;
    private String nickname;
    private String role;
    private String provider;
    private String providerId;
    private Timestamp createDate;
    private Timestamp accessDate;
    private List<UserIngredient> userItems;
    private List<CocktailRecipe> cocktailRecipes;
    private List<IngredientRecipe> ingredientRecipes;
    private List<Posting> postings;
    private List<Reply> replies;

}
