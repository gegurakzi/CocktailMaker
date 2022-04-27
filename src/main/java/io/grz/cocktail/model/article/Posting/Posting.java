package io.grz.cocktail.model.article.Posting;

import io.grz.cocktail.model.article.Article;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Getter
@Setter
@Entity
@Inheritance
public class Posting extends Article {

}
