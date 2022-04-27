package io.grz.cocktail;

import io.grz.cocktail.model.JoinTable.RecipeItem;
import io.grz.cocktail.model.JoinTable.UserIngredient;
import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.Recipe;
import io.grz.cocktail.model.item.Cocktail.Cocktail;
import io.grz.cocktail.model.item.Ingredient.Ingredient;
import io.grz.cocktail.model.item.Item;
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
	private ArticleRepository articleRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RecipeItemRepository recipeItemRepository;
	@Autowired
	private UserIngredientRepository userIngredientRepository;

	@Test
	void contextLoads() {

		User user = new User();
		user.setUsername("manager@cocktail.com");
		user.setPassword("pass");
		user.setNickname("manager");
		user.setRole("MANAGER");

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setName("설탕");
		ingredient1.setDescription("달콤함");
		ingredient1.setUnit("g");
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setName("물");
		ingredient2.setDescription("밍밍함");
		ingredient2.setUnit("ml");
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setName("탄산수");
		ingredient3.setDescription("청량감");
		ingredient3.setUnit("ml");
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setName("위스키");
		ingredient4.setDescription("우디함");
		ingredient4.setUnit("oz");
		Ingredient ingredient5 = new Ingredient();
		ingredient5.setName("레몬즙");
		ingredient5.setDescription("시큼함");
		ingredient5.setUnit("oz");

		Cocktail cocktail1 = new Cocktail();
		cocktail1.setName("칵테일#1");
		cocktail1.setDescription("첫번째 칵테일");
		cocktail1.setUnit("잔");
		Cocktail cocktail2 = new Cocktail();
		cocktail2.setName("칵테일#2");
		cocktail2.setDescription("두번째 칵테일");
		cocktail2.setUnit("잔");
		Cocktail cocktail3 = new Cocktail();
		cocktail3.setName("칵테일#3");
		cocktail3.setDescription("세번째 칵테일");
		cocktail3.setUnit("잔");

		CocktailRecipe recipe1 = new CocktailRecipe();
		recipe1.setAuthor(user);
		recipe1.setTitle("첫번째 칵테일");
		recipe1.setContent("첫번째 칵테일 레시피 입니다");
		recipe1.setResult(cocktail1);
		recipe1.setViews(5);
		List<RecipeItem> recipeItems1 = new ArrayList<>();
		RecipeItem recipeItem1 = new RecipeItem();
		recipeItem1.setRecipe(recipe1);
		recipeItem1.setRecipeItem(ingredient1);
		recipeItem1.setAmount(3);
		RecipeItem recipeItem2 = new RecipeItem();
		recipeItem2.setRecipe(recipe1);
		recipeItem2.setRecipeItem(ingredient2);
		recipeItem2.setAmount(60);
		RecipeItem recipeItem3 = new RecipeItem();
		recipeItem3.setRecipe(recipe1);
		recipeItem3.setRecipeItem(ingredient3);
		recipeItem3.setAmount(30);
		recipeItems1.add(recipeItem1);
		recipeItems1.add(recipeItem2);
		recipeItems1.add(recipeItem3);
		recipe1.setComponents(recipeItems1);

		CocktailRecipe recipe2 = new CocktailRecipe();
		recipe2.setAuthor(user);
		recipe2.setTitle("두번째 칵테일");
		recipe2.setContent("두번째 칵테일 레시피 입니다");
		recipe2.setResult(cocktail2);
		recipe2.setViews(5);
		List<RecipeItem> recipeItems2 = new ArrayList<>();
		RecipeItem recipeItem4 = new RecipeItem();
		recipeItem4.setRecipe(recipe2);
		recipeItem4.setRecipeItem(ingredient2);
		recipeItem4.setAmount(40);
		RecipeItem recipeItem5 = new RecipeItem();
		recipeItem5.setRecipe(recipe2);
		recipeItem5.setRecipeItem(ingredient3);
		recipeItem5.setAmount(50);
		RecipeItem recipeItem6 = new RecipeItem();
		recipeItem6.setRecipe(recipe2);
		recipeItem6.setRecipeItem(ingredient4);
		recipeItem6.setAmount(6);
		recipeItems2.add(recipeItem4);
		recipeItems2.add(recipeItem5);
		recipeItems2.add(recipeItem6);
		recipe2.setComponents(recipeItems2);

		CocktailRecipe recipe3 = new CocktailRecipe();
		recipe3.setAuthor(user);
		recipe3.setTitle("세번째 칵테일");
		recipe3.setContent("세번째 칵테일 레시피 입니다");
		recipe3.setResult(cocktail3);
		recipe3.setViews(5);
		List<RecipeItem> recipeItems3 = new ArrayList<>();
		RecipeItem recipeItem7 = new RecipeItem();
		recipeItem7.setRecipe(recipe3);
		recipeItem7.setRecipeItem(ingredient3);
		recipeItem7.setAmount(7);
		RecipeItem recipeItem8 = new RecipeItem();
		recipeItem8.setRecipe(recipe3);
		recipeItem8.setRecipeItem(ingredient4);
		recipeItem8.setAmount(80);
		RecipeItem recipeItem9 = new RecipeItem();
		recipeItem9.setRecipe(recipe3);
		recipeItem9.setRecipeItem(ingredient5);
		recipeItem9.setAmount(40);
		recipeItems3.add(recipeItem7);
		recipeItems3.add(recipeItem8);
		recipeItems3.add(recipeItem9);
		recipe3.setComponents(recipeItems3);

		userRepository.save(user);
		articleRepository.save(recipe1);
		articleRepository.save(recipe2);
		articleRepository.save(recipe3);
		itemRepository.save(ingredient1);
		itemRepository.save(ingredient2);
		itemRepository.save(ingredient3);
		itemRepository.save(ingredient4);
		itemRepository.save(ingredient5);
		recipeItemRepository.save(recipeItem1);
		recipeItemRepository.save(recipeItem2);
		recipeItemRepository.save(recipeItem3);
		recipeItemRepository.save(recipeItem4);
		recipeItemRepository.save(recipeItem5);
		recipeItemRepository.save(recipeItem6);
		recipeItemRepository.save(recipeItem7);
		recipeItemRepository.save(recipeItem8);
		recipeItemRepository.save(recipeItem9);



	}

}
