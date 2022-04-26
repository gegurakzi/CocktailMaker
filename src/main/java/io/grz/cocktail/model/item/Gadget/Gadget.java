package io.grz.cocktail.model.item.Gadget;

import io.grz.cocktail.model.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Inheritance
public class Gadget extends Item {
}
