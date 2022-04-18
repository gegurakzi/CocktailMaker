package io.grz.cocktail.model.item.Ingredient;

import io.grz.cocktail.model.article.Recipe.IngredientRecipe;
import io.grz.cocktail.model.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Inheritance
public class Ingredient extends Item {

    private  long ideaId;

    @OneToOne(mappedBy = "result")
    private IngredientRecipe ingredientRecipe;

}
