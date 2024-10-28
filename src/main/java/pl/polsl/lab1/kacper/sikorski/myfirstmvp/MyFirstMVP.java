package pl.polsl.lab1.kacper.sikorski.myfirstmvp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller.GameController;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions.InvalidNameException;

/**
 * The main class for the MyFirstMVP application. This class initializes and
 * starts the game using the GameController.
 *
 * It supports running the game with or without command-line arguments.
 *
 * Accessibility options included: - Tooltip guidance for components - Error
 * handling with JOptionPane - Key binding and mnemonic for Confirm button
 *
 * @version 1.0
 */
public class MyFirstMVP extends WindowAdapter implements ActionListener {

    private static JButton defaultButton = null;
    private GameController game = new GameController();
    private JTextField textField1;

    protected final static String CONFIRM_NAME = "Confirm name";

    private static String initialName;

    public MyFirstMVP(String name) {
        initialName = name;
    }

    protected JComponent createOptionControls() {
        JLabel label1 = new JLabel("Name: ");

        textField1 = new JTextField();
        textField1.setToolTipText("Enter your name (3 to 10 characters)");
        if (initialName != null && !initialName.isEmpty()) {
            textField1.setText(initialName);
        }

        JButton button1 = new JButton(CONFIRM_NAME);
        button1.setToolTipText("Click to confirm your name and start the game (Alt+C)");
        button1.setMnemonic(KeyEvent.VK_C);
        button1.addActionListener(this);

        textField1.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "confirmName");
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

    private static void createAndShowGUI(String name) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("RPG Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyFirstMVP demo = new MyFirstMVP(name);

        Container contentPane = frame.getContentPane();
        contentPane.add(demo.createOptionControls(), BorderLayout.CENTER);
        frame.getRootPane().setDefaultButton(defaultButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The method supports two cases: 1. If no arguments are passed, the game
     * will prompt the player for input via the console. 2. If one argument is
     * passed (args[0]), the argument will be treated as the player's name.
     *
     * @param args Arguments for main function args[0] (optional) - the name of
     * the player.
     */
    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "";

        // Run the GUI with the given name argument if present
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI(name));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

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
