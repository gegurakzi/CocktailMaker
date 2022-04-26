package io.grz.cocktail.repository;

import io.grz.cocktail.model.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Transactional
    public int deleteById(Long id);

    public  Article findById(Long id);

}
