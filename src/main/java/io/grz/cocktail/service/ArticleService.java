package io.grz.cocktail.service;

import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import io.grz.cocktail.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public List<CocktailRecipe> getBestCocktailRecipeListRoughly(int itemNum){
        return articleRepository.selectCocktailRecipesByLikesOrder(itemNum);
    }

}
