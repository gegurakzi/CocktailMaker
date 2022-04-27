package io.grz.cocktail.model.item.Cocktail;

import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.Recipe;
import io.grz.cocktail.model.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper=false)
@Inheritance
public class Cocktail extends Item {

    @OneToOne(mappedBy = "result")
    private CocktailRecipe cocktailRecipe;

}
