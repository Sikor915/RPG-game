package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.*;

/**
 * The NPC class represents a non-player character in the game. It extends the
 * Entity class, inheriting properties like health, name, and type. NPCs are
 * characters in the game that are not controlled by the player.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Data
public class NPC extends Entity {

    /**
     * Constructs an NPC object. This constructor initializes a non-player
     * character with default values.
     */
    public NPC() {

    }
}
