package io.grz.cocktail.model.article.Reply;

import io.grz.cocktail.model.article.Article;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Data
@Entity
@Inheritance
public class Reply extends Article {
}
