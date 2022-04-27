package io.grz.cocktail.model.item.Ingredient;

import io.grz.cocktail.model.article.Recipe.IngredientRecipe;
import io.grz.cocktail.model.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Inheritance
public class Ingredient extends Item {

    private  long ideaId;

    @OneToOne(mappedBy = "result")
    private IngredientRecipe ingredientRecipe;

}
