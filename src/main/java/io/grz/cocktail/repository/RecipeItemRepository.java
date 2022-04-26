package io.grz.cocktail.repository;

import io.grz.cocktail.model.JoinTable.RecipeItem;
import io.grz.cocktail.model.article.Recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Integer> {

    List<RecipeItem> findAllByRecipe(Recipe recipe);

}
