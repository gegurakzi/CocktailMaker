package io.grz.cocktail.model.article;


import io.grz.cocktail.model.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private long id;

    @Column(length=100)
   private String title;

    @Lob
    private String content;

    private int views;
    private int likes;

    @CreationTimestamp
    private Timestamp CreateDate;

    private Timestamp editDate;

    @ManyToOne
    private User author;

}
