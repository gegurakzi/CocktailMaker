package io.grz.cocktail.controller;

import io.grz.cocktail.model.JoinTable.RecipeItem;
import io.grz.cocktail.model.JoinTable.UserIngredient;
import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.article.Recipe.Recipe;
import io.grz.cocktail.model.item.Cocktail.Cocktail;
import io.grz.cocktail.model.item.Ingredient.Ingredient;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RecipeItemRepository recipeItemRepository;

    @Autowired
    private UserIngredientRepository userIngredientRepository;

    @GetMapping("/")
    public String index(Model model) {

        //TODO: Authenticate Token

        model.addAttribute("user", null);
        return "index";
    }



}
