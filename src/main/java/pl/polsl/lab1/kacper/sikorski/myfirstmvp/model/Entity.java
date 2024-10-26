package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

/**
 * The Entity class represents a general entity in the game.
 * It serves as a base class for other specific entities like players and enemies, with common properties such as health, name, and type.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class Entity 
{

    /** The health of the entity */
    private int health;

    /** The name of the entity */
    private String name;

    /** The type of the entity (e.g., player, enemy, NPC) */
    private String type;

    /**
     * Gets the health of the entity.
     * 
     * @return The health value
     */
    public int getHealth() 
    {
        return health;
    }

    /**
     * Gets the name of the entity.
     * 
     * @return The name of the entity
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the type of the entity.
     * 
     * @return The type of the entity
     */
    public String getType() 
    {
        return type;
    }

    /**
     * Sets the health of the entity.
     * 
     * @param health The new health value
     */
    public void setHealth(int health) 
    {
        this.health = health;
    }

    /**
     * Sets the name of the entity.
     * 
     * @param name The new name
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Sets the type of the entity.
     * 
     * @param type The new type (e.g., player, enemy, NPC)
     */
    public void setType(String type) 
    {
        this.type = type;
    }

    /**
     * Default constructor for the Entity class.
     * Initializes the entity with default values: health 100, empty name, and empty type.
     */
    public Entity() 
    {
        health = 100;
        name = "";
        type = "";
    }

    /**
     * Parameterized constructor for the Entity class.
     * Initializes the entity with the specified health, name, and type.
     * 
     * @param health The health value
     * @param name The name of the entity
     * @param type The type of the entity
     */
    public Entity(int health, String name, String type) 
    {
        this.health = health;
        this.name = name;
        this.type = type;
    }
}
