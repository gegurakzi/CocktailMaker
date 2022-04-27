package io.grz.cocktail.model.article.Recipe;

import io.grz.cocktail.model.item.Ingredient.Ingredient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Inheritance
public class IngredientRecipe extends Recipe{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESULT_INGREDIENT_ID")
    private Ingredient result;

}
