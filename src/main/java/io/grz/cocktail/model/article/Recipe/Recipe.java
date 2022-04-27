package io.grz.cocktail.model.article.Recipe;

import io.grz.cocktail.model.JoinTable.RecipeItem;
import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.article.Reply.Reply;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance
public class Recipe extends Article {

    private String signature;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeItem> components = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "RECIPE_REPLY", joinColumns = @JoinColumn(name="REPLY_ID"), inverseJoinColumns = @JoinColumn(name = "RECIPE_ID"))
    private  List<Reply> replies;

}
