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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserIngredientRepository userIngredientRepository;

	@Autowired
	private RecipeItemRepository recipeItemRepository;

	@Test
	void contextLoads() {
		User user = new User();
		user.setUsername("길동");
		user.setPassword("안녕");
		userRepository.save(user);

		Article article = new Article();
		article.setAuthor(user);
		articleRepository.save(article);

		Ingredient sugar = new Ingredient();
		sugar.setName("sugar");
		Ingredient water = new Ingredient();
		water.setName("water");
		itemRepository.save(sugar);



		UserIngredient userIngredient1 = new UserIngredient();
		UserIngredient userIngredient2 = new UserIngredient();
		userIngredient1.setOwner(user);
		userIngredient2.setOwner(user);
		userIngredient1.setIngredient(sugar);
		userIngredient2.setIngredient(water);

		userIngredientRepository.save(userIngredient1);
		userIngredientRepository.save(userIngredient2);

		List<UserIngredient> userIngredients = new ArrayList<>();
		userIngredients.add(userIngredient1);
		userIngredients.add(userIngredient2);

		user.setUserItems(userIngredients);
		userRepository.save(user);

		System.out.println(user.getUserItems().get(0).getIngredient().getName());
		System.out.println(user.getUserItems().get(1).getIngredient().getName());

		Cocktail cocktail = new Cocktail();
		cocktail.setName("Manhattan");

		CocktailRecipe recipe = new CocktailRecipe();
		recipe.setAuthor(user);
		recipe.setResult(cocktail);

		List<RecipeItem> recipeItems = new ArrayList<>();
		RecipeItem recipeItem1 = new RecipeItem();
		recipeItem1.setRecipeItem(sugar);
		recipeItem1.setRecipe(recipe);
		recipeItem1.setAmount(1);
		RecipeItem recipeItem2 = new RecipeItem();
		recipeItem2.setRecipeItem(water);
		recipeItem2.setRecipe(recipe);
		recipeItem2.setAmount(1);

		recipeItems.add(recipeItem1);
		recipeItems.add(recipeItem2);
		recipe.setComponents(recipeItems);

		recipeItemRepository.save(recipeItem1);
		recipeItemRepository.save(recipeItem2);
		articleRepository.save(recipe);


		System.out.println(recipeItemRepository.findAllByRecipe(recipe));


		List<RecipeItem> recipeItemFound = recipeItemRepository.findAllByRecipe(recipe);

		Ingredient ingredientFound = (Ingredient) recipeItemFound.get(1).getRecipeItem();
		Recipe recipeFound = recipeItemFound.get(1).getRecipe();

		System.out.println(ingredientFound.getName());
		System.out.println(recipeFound.getAuthor().getUsername());

		long id = 2;

		recipeItemRepository.delete(recipeItem1);
		recipeItemRepository.delete(recipeItem2);
		int res = articleRepository.deleteById(id); // 연관 엔티티를 삭제해야 삭제 가능
		System.out.println(articleRepository.findById(id));
	}

}
