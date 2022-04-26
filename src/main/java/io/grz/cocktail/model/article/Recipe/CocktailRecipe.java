package io.grz.cocktail.model.article.Recipe;

import io.grz.cocktail.model.item.Cocktail.Cocktail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance
public class CocktailRecipe extends Recipe{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESULT_COCKTAIL_ID")
    private Cocktail result;

}
