package pl.polsl.lab1.kacper.sikorski.myfirstmvp.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller.GameController;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions.InvalidNameException;

/**
 * The GUI class for the MyFirstMVP application. This class initializes and
 * manages the graphical user interface.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class GUI {

    /**
     * Instance of the game controller to manage the game logic.
     */
    private GameController game = new GameController();

    /**
     * Text field for user input to enter their name.
     */
    private JTextField textField1;

    /**
     * Constant string for the confirm name button.
     */
    protected final static String CONFIRM_NAME = "Confirm name";

    /**
     * Constructor for the GUI class. Initializes the GUI components.
     *
     * @param name The name of the player, if provided.
     */
    public GUI(String name) {
        createAndShowGUI(name);
    }

    /**
     * Creates and displays the graphical user interface.
     *
     * @param name The name of the player, if provided.
     */
    private void createAndShowGUI(String name) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {

        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("RPG Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.add(createOptionControls(name), BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Creates the controls for user options, such as entering and confirming
     * their name.
     *
     * @param name The name of the player, if provided.
     * @return A JComponent containing the controls.
     */
    protected JComponent createOptionControls(String name) {
        JLabel label1 = new JLabel("Name: ");

        textField1 = new JTextField();
        textField1.setToolTipText("Enter your name (3 to 10 characters)");

        if (name != null && !name.isEmpty()) {
            textField1.setText(name);
        }

        JButton button1 = new JButton(CONFIRM_NAME);
        button1.setToolTipText("Click to confirm your name and start the game (Alt+C)");
        button1.setMnemonic(KeyEvent.VK_C);
        button1.addActionListener(this::handleConfirmName);

        textField1.getInputMap(JComponent.WHEN_FOCUSED)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "confirmName");
        textField1.getActionMap().put("confirmName", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.doClick();
            }
        });

        Box box = Box.createVerticalBox();
        box.add(label1);
        box.add(Box.createVerticalStrut(5));
        box.add(textField1);
        box.add(Box.createVerticalStrut(10));
        box.add(button1);

        return box;
    }

    /**
     * Handles the action of confirming the name entered by the user.
     *
     * @param e The action event triggered by the button click.
     */
    private void handleConfirmName(ActionEvent e) {
        // Get the action command from the event
        String command = e.getActionCommand();

        // Check if the command matches the confirm name action
        if (CONFIRM_NAME.equals(command)) {
            String name = textField1.getText();
            try {
                if (name.isEmpty() || name.length() < 3 || name.length() > 10) {
                    throw new InvalidNameException("ERROR: Name should be between 3 to 10 characters long.");
                }
                game.gameRun(name);
            } catch (InvalidNameException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Name", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
