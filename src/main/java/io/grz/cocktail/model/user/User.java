package io.grz.cocktail.model.user;

import io.grz.cocktail.model.article.Posting.Posting;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.IngredientRecipe;
import io.grz.cocktail.model.article.Reply.Reply;
import io.grz.cocktail.model.item.Item;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long id;

    @Column(unique = true, length=50)
    private String username;

    @Column(nullable = false, length=75)
    private String password;

    private String email;
    private String role;
    private String provider;
    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp accessDate;

    //User < User_UserItem > UserItem
    @ManyToMany
    @JoinTable(name = "USER_USERITEM",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")})
    private List<Item> userItems = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<CocktailRecipe> cocktailRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<IngredientRecipe> ingredientRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Posting> postings = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Reply> replies = new ArrayList<>();

}
