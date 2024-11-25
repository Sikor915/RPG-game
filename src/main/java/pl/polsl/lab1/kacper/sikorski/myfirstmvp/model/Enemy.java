package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an enemy entity in the game, extending the Entity class. An enemy
 * has a specific health value, name, type, and a reward for defeating it. This
 * class uses Lombok to generate getter methods and the constructor.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Enemy extends Entity {

    private final int reward; // Immutable field, no setter is generated

}
