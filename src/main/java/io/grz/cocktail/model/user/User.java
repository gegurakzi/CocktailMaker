package io.grz.cocktail.model.user;

import io.grz.cocktail.model.JoinTable.UserIngredient;
import io.grz.cocktail.model.article.Posting.Posting;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.IngredientRecipe;
import io.grz.cocktail.model.article.Reply.Reply;
import io.grz.cocktail.model.item.Ingredient.Ingredient;
import io.grz.cocktail.model.item.Item;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long id;

    @Column(nullable = false, unique = true, length=50)
    private String username;

    @Column(nullable = false, length=75)
    private String password;

    @Column(nullable = false, length=50)
    private String nickname;

    @Column(nullable = false)
    private String role;

    private String provider;
    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp accessDate;

    //User < User_UserItem > UserItem
    @OneToMany(mappedBy = "owner")
    private List<UserIngredient> userItems = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<CocktailRecipe> cocktailRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<IngredientRecipe> ingredientRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Posting> postings = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Reply> replies = new ArrayList<>();

}
