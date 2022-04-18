package io.grz.cocktail;

import io.grz.cocktail.model.article.Article;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.ArticleRepository;
import io.grz.cocktail.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CocktailApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void contextLoads() {
		User user = new User();
		user.setUsername("길동");
		user.setPassword("안녕");
		userRepository.save(user);

		Article article = new Article();
		article.setAuthor(user);
		articleRepository.save(article);

	}

}
