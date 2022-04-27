package io.grz.cocktail.model.item.Gadget;

import io.grz.cocktail.model.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper=false)
@Inheritance
public class Gadget extends Item {
}
