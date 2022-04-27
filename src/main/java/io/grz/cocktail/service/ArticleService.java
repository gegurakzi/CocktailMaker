package io.grz.cocktail.service;

import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.model.item.Cocktail.Cocktail;
import io.grz.cocktail.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public List<CocktailRecipe> getAllCocktailRecipe(){
        return articleRepository.findAll().stream().map(e -> (CocktailRecipe) e).collect(Collectors.toList());
    }

    @Transactional
    public List<CocktailRecipe> getBestCocktailRecipeListRoughly(int itemNum){
        return articleRepository.selectCocktailRecipesByLikesOrder(itemNum);
    }

}
