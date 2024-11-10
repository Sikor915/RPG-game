package pl.polsl.lab1.kacper.sikorski.myfirstmvp.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller.GameController;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Player;

/**
 * The GameWindow class represents the main game GUI that displays player
 * information, enemy information, and provides options to view inventory, start
 * a fight, and exit the game.
 *
 * @author Kacper
 * @version 1.0
 */
public class GameWindow extends JFrame {

    private final GameController gameController;

    private JLabel playerNameLabel;
    private JLabel playerHealthLabel;
    private JLabel playerWeaponLabel;
    private JLabel battleResultLabel;

    private JLabel enemyNameLabel;
    private JLabel enemyHealthLabel;

    private JButton viewInventoryButton;
    private JButton startFightButton;
    private JButton exitGameButton;

    /**
     * Constructs the GameWindow and initializes the UI components.
     *
     * @param gameController the game controller managing the game logic
     */
    public GameWindow(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Initializes and arranges all components in the game window.
     */
    public void initializeUI() {
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Player Info Panel
        JPanel playerInfoPanel = new JPanel(new GridLayout(3, 1));
        playerNameLabel = new JLabel("Player Name: " + gameController.getPlayer().getName());
        playerHealthLabel = new JLabel("Health: " + gameController.getPlayer().getHealth());
        playerWeaponLabel = new JLabel("Weapon: " + gameController.getPlayer().getWeapons().get(0).getName() + ", " + gameController.getPlayer().getWeapons().get(0).getDamage() + " damage.");
        battleResultLabel = new JLabel("");

        playerInfoPanel.add(playerNameLabel);
        playerInfoPanel.add(playerHealthLabel);
        playerInfoPanel.add(playerWeaponLabel);

        // Enemy Info Panel
        JPanel enemyInfoPanel = new JPanel(new GridLayout(2, 1));
        enemyNameLabel = new JLabel("Enemy Name: ");
        enemyHealthLabel = new JLabel("Enemy Health: ");

        enemyInfoPanel.add(enemyNameLabel);
        enemyInfoPanel.add(enemyHealthLabel);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        viewInventoryButton = new JButton("View Inventory");
        startFightButton = new JButton("Start Fight");
        exitGameButton = new JButton("Exit Game");

        // Set button actions
        viewInventoryButton.addActionListener(e -> showInventoryWindow());
        startFightButton.addActionListener(e -> gameController.getFightController().startFight(gameController.getPlayer()));
        exitGameButton.addActionListener(e -> System.exit(0));

        // Add tooltips for accessibility
        viewInventoryButton.setToolTipText("View your inventory items (Alt+I)");
        startFightButton.setToolTipText("Start a fight with the enemy (Alt+F)");
        exitGameButton.setToolTipText("Exit the game (Alt+E)");

        // Set mnemonics (shortcuts)
        viewInventoryButton.setMnemonic(KeyEvent.VK_I);
        startFightButton.setMnemonic(KeyEvent.VK_F);
        exitGameButton.setMnemonic(KeyEvent.VK_E);

        buttonsPanel.add(viewInventoryButton);
        buttonsPanel.add(startFightButton);
        buttonsPanel.add(exitGameButton);

        // Assemble the main layout
        add(playerInfoPanel, BorderLayout.WEST);
        add(enemyInfoPanel, BorderLayout.EAST);
        add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Shows a separate window displaying the player's inventory in a scrollable
     * table.
     */
    public void showInventoryWindow() {
        JDialog inventoryDialog = new JDialog(this, "Player Inventory", true);
        inventoryDialog.setSize(400, 300);
        inventoryDialog.setLayout(new BorderLayout());

        // Table model and table setup
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Item");
        tableModel.addColumn("Quantity");

        // Populate table with inventory items from GameController
        gameController.getPlayer().getPlayerInventory().getItems().forEach(item -> {
            tableModel.addRow(new Object[]{item.getName(), item.getQuantity()});
        });

        JTable inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        inventoryDialog.add(scrollPane, BorderLayout.CENTER);
        inventoryDialog.setLocationRelativeTo(this);
        inventoryDialog.setVisible(true);
    }

    /**
     * Updates the displayed player health.
     */
    public void updatePlayerHealth() {
        playerHealthLabel.setText("Health: " + gameController.getPlayer().getHealth());
    }

    /**
     * Updates the displayed enemy information.
     *
     * @param name the enemy's name
     * @param health the enemy's health
     */
    public void updateEnemyInfo(String name, int health) {
        enemyNameLabel.setText("Enemy Name: " + name);
        enemyHealthLabel.setText("Enemy Health: " + health);
    }

    /**
     * Displays the result of the battle in the GUI.
     *
     * @param result The result of the battle ("Victory" or "Defeat")
     * @param reward The reward received from the battle if victorious
     */
    public void displayBattleResult(String result, int reward) {
        String resultMessage;

        if ("Victory".equals(result)) {
            resultMessage = "Victory! You earned " + reward + " gold.";
        } else {
            resultMessage = "Defeat! Better luck next time.";
        }

        // Display the result message in the battleResultLabel
        battleResultLabel.setText(resultMessage);

        // Optionally show a dialog as well
        JOptionPane.showMessageDialog(this, resultMessage, "Battle Result", JOptionPane.INFORMATION_MESSAGE);

        if (gameController.getPlayer().getHealth() <= 0) {
            System.exit(0);
        }
    }

    public JButton getViewInventoryButton() {
        return viewInventoryButton;
    }

    public JButton getStartFightButton() {
        return startFightButton;
    }

    public JButton getExitGameButton() {
        return exitGameButton;
    }
}
