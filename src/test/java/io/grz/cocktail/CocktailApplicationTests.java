package io.grz.cocktail;

import io.grz.cocktail.model.JoinTable.RecipeItem;
import io.grz.cocktail.model.JoinTable.UserIngredient;
import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.Recipe;
import io.grz.cocktail.model.item.Cocktail.Cocktail;
import io.grz.cocktail.model.item.Ingredient.Ingredient;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CocktailApplicationTests {

	@Test
	void contextLoads() {

	}

}
