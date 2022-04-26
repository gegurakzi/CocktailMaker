package io.grz.cocktail.repository;

import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.article.Recipe.CocktailRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Transactional
    public int deleteById(Long id);

    public  Article findById(Long id);

    @Query(value="SELECT * FROM article ORDER BY likes DESC LIMIT ?1", nativeQuery = true)
    public List<CocktailRecipe> selectCocktailRecipesByLikesOrder(int rows);
}
