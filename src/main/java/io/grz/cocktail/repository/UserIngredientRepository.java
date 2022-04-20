package io.grz.cocktail.repository;

import io.grz.cocktail.model.JoinTable.RecipeItem;
import io.grz.cocktail.model.JoinTable.UserIngredient;
import io.grz.cocktail.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIngredientRepository extends JpaRepository<UserIngredient, Integer> {

    List<UserIngredient> findAllByOwner(User user);

}
