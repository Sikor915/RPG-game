package pl.polsl.lab1.kacper.sikorski.myfirstmvp.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller.GameController;
import lombok.*;

/**
 * The GameWindow class represents the main game GUI that displays player
 * information, enemy information, and provides options to view inventory, start
 * a fight, and exit the game.
 *
 * @author Kacper
 * @version 1.0
 */
@Getter
@Setter
public class GameWindow extends JFrame {

    /**
     * GameController instance to manage game logic and state.
     */
    private final GameController gameController;

    /**
     * Label that displays player name.
     */
    private JLabel playerNameLabel;

    /**
     * Label that displays player health.
     */
    private JLabel playerHealthLabel;

    /**
     * Label that displays player's weapon.
     */
    private JLabel playerWeaponLabel;

    /**
     * label that displays the battle result (Victory/Defeat).
     */
    private JLabel battleResultLabel;

    /**
     * Label that displays enemy name.
     */
    private JLabel enemyNameLabel;

    /**
     * Label that displays enemy health.
     */
    private JLabel enemyHealthLabel;

    /**
     * Button that is used to display inventory.
     */
    private JButton viewInventoryButton;

    /**
     * Button that is used to start the fight.
     */
    private JButton startFightButton;

    /**
     * Button that is used to exit the game.
     */
    private JButton exitGameButton;

    /**
     * Button that is used to attack the enemy during fight.
     */
    private JButton attackButton;

    /**
     * Button that is used to use an item.
     */
    private JButton useItemButton;

    /**
     * String that is used to determine player's chosen action.
     */
    private String playerAction;

    /**
     * Constructs the GameWindow and initializes the UI components.
     *
     * @param gameController the game controller managing the game logic.
     */
    public GameWindow(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Initializes and arranges all components in the game window.
     */
    public void initializeUI() {
        // Set up the main frame properties
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the player information panel
        JPanel playerInfoPanel = new JPanel(new GridLayout(3, 1));
        playerNameLabel = new JLabel("Player Name: " + gameController.getPlayer().getName());
        playerHealthLabel = new JLabel("Health: " + gameController.getPlayer().getHealth());
        playerWeaponLabel = new JLabel("Weapon: " + gameController.getPlayer().getWeapons().get(0).getName()
                + ", " + gameController.getPlayer().getWeapons().get(0).getDamage() + " damage.");
        battleResultLabel = new JLabel(""); // Placeholder for battle results

        // Add labels to the player info panel
        playerInfoPanel.add(playerNameLabel);
        playerInfoPanel.add(playerHealthLabel);
        playerInfoPanel.add(playerWeaponLabel);

        // Create the enemy information panel
        JPanel enemyInfoPanel = new JPanel(new GridLayout(2, 1));
        enemyNameLabel = new JLabel("Enemy Name: ");
        enemyHealthLabel = new JLabel("Enemy Health: ");

        // Add labels to the enemy info panel
        enemyInfoPanel.add(enemyNameLabel);
        enemyInfoPanel.add(enemyHealthLabel);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        viewInventoryButton = new JButton("View Inventory");
        startFightButton = new JButton("Start Fight");
        exitGameButton = new JButton("Exit Game");

        // Create buttons for combat and add tooltips for accessibility
        attackButton = new JButton("Attack");
        useItemButton = new JButton("Use Item");

        attackButton.setToolTipText("Attack the enemy {Alt + A)");
        useItemButton.setToolTipText("Use a healing potion (Alt + I)");

        attackButton.setMnemonic(KeyEvent.VK_A);
        useItemButton.setMnemonic(KeyEvent.VK_I);

        // Set actions for the buttons
        viewInventoryButton.addActionListener(e -> showInventoryWindow());
        startFightButton.addActionListener(e -> gameController.getFightController().startFight(gameController.getPlayer()));
        exitGameButton.addActionListener(e -> System.exit(0));

        // Add tooltips for better accessibility
        viewInventoryButton.setToolTipText("View your inventory items (Alt+I)");
        startFightButton.setToolTipText("Start a fight with the enemy (Alt+F)");
        exitGameButton.setToolTipText("Exit the game (Alt+E)");

        // Set mnemonics (keyboard shortcuts) for buttons
        viewInventoryButton.setMnemonic(KeyEvent.VK_I);
        startFightButton.setMnemonic(KeyEvent.VK_F);
        exitGameButton.setMnemonic(KeyEvent.VK_E);

        // Add buttons to the buttons panel
        buttonsPanel.add(viewInventoryButton);
        buttonsPanel.add(startFightButton);
        buttonsPanel.add(exitGameButton);

        // Add panels to the main frame
        add(playerInfoPanel, BorderLayout.WEST);
        add(enemyInfoPanel, BorderLayout.EAST);
        add(buttonsPanel, BorderLayout.SOUTH);

        // Finalize and display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Shows a separate window displaying the player's inventory in a scrollable
     * table.
     */
    public void showInventoryWindow() {
        // Create a modal dialog for the inventory
        JDialog inventoryDialog = new JDialog(this, "Player Inventory", true);
        inventoryDialog.setSize(400, 300);
        inventoryDialog.setLayout(new BorderLayout());

        // Set up the table model and table
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Item");
        tableModel.addColumn("Quantity");

        // Populate the table with items from the player's inventory
        gameController.getPlayer().getPlayerInventory().getItems().forEach(item -> {
            tableModel.addRow(new Object[]{item.getName(), item.getQuantity()});
        });

        JTable inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        // Add the table to the dialog
        inventoryDialog.add(scrollPane, BorderLayout.CENTER);
        inventoryDialog.setLocationRelativeTo(this);

        inventoryDialog.setVisible(true);
    }

    /**
     * Updates the displayed player health information.
     */
    public void updatePlayerHealth() {
        playerHealthLabel.setText("Health: " + gameController.getPlayer().getHealth());
    }

    /**
     * Updates the displayed enemy information.
     *
     * @param name the enemy's name.
     * @param health the enemy's health.
     */
    public void updateEnemyInfo(String name, int health) {
        enemyNameLabel.setText("Enemy Name: " + name + " ");
        enemyHealthLabel.setText("Enemy Health: " + health + " ");
    }

    /**
     * Updates the displayed player information
     *
     * @param health
     * @param weaponName
     * @param weaponDamage
     */
    public void updatePlayerInfo(int health, String weaponName, int weaponDamage) {
        playerHealthLabel.setText("Health: " + health + " ");
        playerWeaponLabel.setText("Weapon: " + weaponName + ", " + weaponDamage + " damage.");
    }

    /**
     * Displays the result of the battle in the GUI.
     *
     * @param result The result of the battle ("Victory" or "Defeat").
     * @param reward The reward received from the battle if victorious.
     */
    public void displayBattleResult(String result, int reward) {
        String resultMessage;

        if ("Victory".equals(result)) {
            resultMessage = "Victory! You earned " + reward + " gold.";
        } else {
            resultMessage = "Defeat! Better luck next time.";
        }

        // Update the result label
        battleResultLabel.setText(resultMessage);

        // Optionally show a dialog for the battle result
        JOptionPane.showMessageDialog(this, resultMessage, "Battle Result", JOptionPane.INFORMATION_MESSAGE);

        // Exit the game if the player's health reaches zero
        if (gameController.getPlayer().getHealth() <= 0) {
            System.exit(0);
        }
    }

    /**
     * Returns the button that opens player inventory
     *
     * @return button to view inventory
     */
    public JButton getViewInventoryButton() {
        return viewInventoryButton;
    }

    /**
     * Returns the button that starts the fight
     *
     * @return button that starts the fight
     */
    public JButton getStartFightButton() {
        return startFightButton;
    }

    /**
     * Returns the button that exits the game
     *
     * @return button to exit the game
     */
    public JButton getExitGameButton() {
        return exitGameButton;
    }

    /**
     * Returns the player's chosen action during a fight.
     *
     * @return the chosen action ("Attack" or "Use Item").
     */
    public String getPlayerAction() {
        return playerAction;
    }

    /**
     * Sets the player's chosen action during a fight.
     *
     * @param string Text which playerAction will be set to.
     */
    public void setPlayerAction(String string) {
        playerAction = string;
    }

    /**
     * Displays the combat options.
     */
    public void displayBattleOptions() {

        JDialog battleDialog = new JDialog(this, "Choose your action", true);
        battleDialog.setLayout(new BoxLayout(battleDialog.getContentPane(), BoxLayout.Y_AXIS));

        attackButton.addActionListener(e -> {
            playerAction = "Attack";
            battleDialog.dispose();
        });

        useItemButton.addActionListener(e -> {
            playerAction = "Use Item";
            battleDialog.dispose();
        });

        // Add buttons to the dialog
        battleDialog.add(attackButton);
        battleDialog.add(useItemButton);

        // Display the dialog
        battleDialog.pack();
        battleDialog.setLocationRelativeTo(this);
        battleDialog.setVisible(true);
    }

    /**
     * Displays a message to the player during combat.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Combat Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
