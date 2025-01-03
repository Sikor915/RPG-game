package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.GameWindow;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
import java.util.Random;
import lombok.*;

/**
 * Handles the combat mechanics between the player and enemies.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Data
public class FightController {

    /**
     * View class responsible for displaying fight details
     */
    private GameWindow gameWindow;

    /**
     * Class responsible for spawning enemies
     */
    private EnemySpawner enemySpawner;

    /**
     * Random number generator for enemy attacks
     */
    private Random random;

    /**
     * The weapon chosen by the player for the fight
     */
    Optional<Weapon> strongestWeapon;

    /**
     * Constructor to initialize the FightController with necessary components.
     * It sets up the view for displaying the fight and initializes the random
     * generator and enemy spawner.
     *
     * @param gameWindow The GameWindow for displaying and handling
     * interactions.
     */
    public FightController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        enemySpawner = new EnemySpawner();
        random = new Random();
    }

    /**
     * Starts a fight between the player and a randomly spawned enemy. Updates
     * the GUI with the enemy's name and health, and continues the fight until
     * either the player or the enemy is defeated.
     *
     * @param player The player participating in the fight.
     */
    public void startFight(Player player) {
        Enemy enemy = enemySpawner.spawnRandomEnemy();
        if (enemy != null) {
            // Notify the GameWindow to display enemy information
            gameWindow.updateEnemyInfo(enemy.getName(), enemy.getHealth());

            strongestWeapon = player.getWeapons().stream().max(Comparator.comparingInt(Weapon::getDamage));

            gameWindow.updatePlayerInfo(player.getHealth(), strongestWeapon.get().getName(), strongestWeapon.get().getDamage());

            OptionalInt damageReduction = player.getPlayerInventory().getItems().stream()
                    .filter(item -> item instanceof Armor)
                    .mapToInt(item -> ((Armor) item).getDamageReduction())
                    .max();
            int finalDamageReduction = damageReduction.orElse(0);

            while (player.getHealth() > 0 && enemy.getHealth() > 0) {

                gameWindow.displayBattleOptions();

                // Get player's action from the GUI
                String action = gameWindow.getPlayerAction();

                manageAction(action, enemy, player);

                if (enemy.getHealth() > 0) {
                    int enemyAttack = random.nextInt(15) + 1;
                    int effectiveDamage = Math.max(0, enemyAttack - finalDamageReduction); // Ensure damage isn't negative
                    player.setHealth(player.getHealth() - effectiveDamage);
                }

                // Update GameWindow after each turn
                gameWindow.updatePlayerHealth();
                gameWindow.updateEnemyInfo(enemy.getName(), enemy.getHealth());

                if (enemy.getHealth() <= 0) {
                    manageDrops(player, enemy);
                    break;
                }

                if (player.getHealth() <= 0) {
                    gameWindow.displayBattleResult("Defeat", 0);
                    break;
                }
            }
        }
        else{
            gameWindow.displayMessage("Somehow, the enemy rolled was equal to NULL. How did we get here?");
        }

    }

    /**
     * Manages the action taken by the player during their turn in combat.
     * Depending on the action ("Attack" or "Use Item"), it either performs an
     * attack on the enemy or uses a healing item (e.g., Healing Potion) from
     * the player's inventory.
     *
     * @param action The action chosen by the player ("Attack" or "Use Item").
     * @param enemy The enemy currently engaged in combat with the player.
     * @param player The player performing the action.
     */
    public void manageAction(String action, Enemy enemy, Player player) {

        if ("Attack".equals(action)) {
            // Attack logic
            int weaponDamage = strongestWeapon.map(Weapon::getDamage).orElse(5);
            enemy.setHealth(enemy.getHealth() - weaponDamage);
            gameWindow.setPlayerAction("");
        } else if ("Use Item".equals(action)) {
            // Using an healing item logic
            boolean itemUsed = player.getPlayerInventory().useHealingItem(player);
            if (itemUsed) {
                gameWindow.displayMessage("You used a Healing Potion and restored 20 health");
                gameWindow.updatePlayerHealth();
            } else {
                gameWindow.displayMessage("You don't have any Healing Potions!");
            }
            gameWindow.setPlayerAction("");
        }
    }

    /**
     * Manages the loot drops after the player defeats an enemy. Based on
     * certain conditions, such as the enemy's type, the player may receive
     * items like gold or armor.
     *
     * @param player The player who has defeated the enemy and is eligible for
     * loot.
     * @param enemy The enemy that was defeated, potentially affecting the loot
     * dropped.
     */
    public void manageDrops(Player player, Enemy enemy) {

        gameWindow.displayBattleResult("Victory", enemy.getReward());
        int potionDrop = random.nextInt(20) + 1;
        int potionCount = random.nextInt(3) + 1;
        if (potionDrop >= 8) {
            player.getPlayerInventory().addItemsToInventory(
                    new Item("Healing Potion", potionCount, Item.Type.POTION),
                    new Item("Gold", enemy.getReward(), Item.Type.GOLD)
            );
            gameWindow.displayMessage("You have acquired " + potionCount + " healing potions.");
        } else {
            player.getPlayerInventory().addItem(new Item("Gold", enemy.getReward(), Item.Type.GOLD));
        }

        if (null != enemy.getName()) {
            switch (enemy.getName()) {
                case "Human Fighter" -> {
                    int armorDropChance = random.nextInt(100) + 1;
                    int weaponDropChance = random.nextInt(100) + 1;
                    // Armor drop logic (30% chance)
                    if (armorDropChance >= 70) {
                        int armorDamageReduction = random.nextInt(5) + 1;
                        Armor droppedArmor = Armor.armorBuilder()
                                .name("Knight's Armor (" + armorDamageReduction + ")")
                                .quantity(1)
                                .type(Item.Type.ARMOR)
                                .damageReduction(armorDamageReduction)
                                .build();
                        player.getPlayerInventory().addItem(droppedArmor);
                        gameWindow.displayMessage("You have acquired armor: " + droppedArmor.getName()
                                + " (Damage Reduction: " + droppedArmor.getDamageReduction() + ").");
                    }       // Weapon drop logic (20% chance)
                    if (weaponDropChance >= 80) {
                        int weaponDamage = random.nextInt((25 - 15) + 1) + 15;
                        Weapon droppedWeapon = Weapon.weaponBuilder()
                                .name("Greatsword (" + weaponDamage + ") ")
                                .quantity(1)
                                .damage(weaponDamage)
                                .type(Item.Type.WEAPON)
                                .build();
                        player.getWeapons().add(droppedWeapon);
                        gameWindow.displayMessage("You have acquired a weapon: " + droppedWeapon.getName()
                                + " (Damage: " + droppedWeapon.getDamage() + ").");
                    }
                }
                case "Goblin" -> {
                    int weaponDropChance = random.nextInt(100) + 1;
                    // Weapon drop logic for Goblin (70% chance)
                    if (weaponDropChance >= 30) {
                        int weaponDamage = random.nextInt(10) + 1;
                        Weapon droppedWeapon = Weapon.weaponBuilder()
                                .name("Knife (" + weaponDamage + ") ")
                                .quantity(1)
                                .damage(weaponDamage)
                                .type(Item.Type.WEAPON)
                                .build();
                        player.getWeapons().add(droppedWeapon);
                        gameWindow.displayMessage("You have acquired a weapon: " + droppedWeapon.getName()
                                + " (Damage: " + droppedWeapon.getDamage() + ").");
                    }
                }
                case "Orc", "Troll" -> {
                    int weaponDropChance = random.nextInt(100) + 1;
                    // Weapon drop logic for Orc/Troll (30% chance)
                    if (weaponDropChance >= 70) {
                        int weaponDamage = random.nextInt((35 - 20) + 1) + 20;
                        Weapon droppedWeapon = Weapon.weaponBuilder()
                                .name("Greatclub (" + weaponDamage + ") ")
                                .quantity(1)
                                .damage(weaponDamage)
                                .type(Item.Type.WEAPON)
                                .build();
                        player.getWeapons().add(droppedWeapon);
                        gameWindow.displayMessage("You have acquired a weapon: " + droppedWeapon.getName()
                                + " (Damage: " + droppedWeapon.getDamage() + ").");
                    }
                }
                default -> {
                }
            }
        }
    }
}
