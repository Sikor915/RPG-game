package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.*;
import lombok.experimental.NonFinal;

/**
 * The Entity class represents a general entity in the game. It serves as a base
 * class for specific entities like players and enemies, with common properties
 * such as health, name, and type.
 *
 * Uses Lombok annotations for boilerplate code reduction.
 *
 * @author Kacper Sikorski
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NonFinal
public class Entity {

    /**
     * The health of the entity.
     */
    private int health = 100;

    /**
     * The name of the entity.
     */
    private String name = "";

    /**
     * The type of the entity (e.g., player, enemy, NPC).
     */
    private String type = "";
}
