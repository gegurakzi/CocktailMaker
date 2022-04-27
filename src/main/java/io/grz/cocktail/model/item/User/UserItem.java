package io.grz.cocktail.model.item.User;

import io.grz.cocktail.model.item.Item;
import io.grz.cocktail.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper=false)
@Inheritance
public class UserItem extends Item {

    private String status;// OWNED, PENDING

}
