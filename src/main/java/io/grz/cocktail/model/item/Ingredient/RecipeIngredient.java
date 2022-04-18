package io.grz.cocktail.model.item.Ingredient;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Inheritance
public class RecipeIngredient extends Ingredient {

}
