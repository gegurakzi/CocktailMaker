package io.grz.cocktail.model.item;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private  long id;

    private String name;
    private String unit;
    private String description;

}
