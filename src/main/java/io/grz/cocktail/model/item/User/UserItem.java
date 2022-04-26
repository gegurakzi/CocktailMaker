package io.grz.cocktail.model.item.User;

import io.grz.cocktail.model.item.Item;
import io.grz.cocktail.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Inheritance
public class UserItem extends Item {

    private String status;// OWNED, PENDING

}
