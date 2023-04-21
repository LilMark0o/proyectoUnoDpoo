package console;

import javax.swing.*;

import controllerPack.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInLogOut extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;

    private String username;
    private String password;

    public LogInLogOut() {
        setTitle("Login");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Ingresar");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                password = new String(passwordField.getPassword());
                if (Controller.logIn(username, password)) {
                    showSuccessFrame();
                    mainInterface.logedIn = true;
                    mainInterface.actualizarEstadoBotones();
                    dispose();
                } else {
                    showErrorFrame();
                    mainInterface.logedIn = false;
                    mainInterface.actualizarEstadoBotones();
                }

            }
        });
        exitButton = new JButton("Atrás");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void addComponentsToFrame() {
        add(new JLabel(""));
        add(new JLabel(""));
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        add(userLabel);

        add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        add(passwordLabel);

        add(passwordField);
        add(new JLabel(""));
        add(new JLabel(""));
        add(loginButton);
        add(exitButton);
    }

    private void showSuccessFrame() {
        JFrame successFrame = new JFrame("Éxito");
        successFrame.setBackground(Color.WHITE);
        successFrame.setSize(300, 200);
        successFrame.setLocationRelativeTo(null);

        ImageIcon successIcon = new ImageIcon("./data/images/check.png");
        JLabel successLabel = new JLabel("Sesión iniciada correctamente.", successIcon, JLabel.CENTER);
        successLabel.setVerticalTextPosition(JLabel.BOTTOM);
        successLabel.setHorizontalTextPosition(JLabel.CENTER);
        successFrame.add(successLabel);

        successFrame.setVisible(true);
    }

    private void showErrorFrame() {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setBackground(Color.WHITE);
        errorFrame.setSize(300, 200);
        errorFrame.setLocationRelativeTo(null);

        ImageIcon errorIcon = new ImageIcon("./data/images/error.png");
        JLabel errorLabel = new JLabel("Error: Usuario o contraseña incorrectos.", errorIcon, JLabel.CENTER);
        errorLabel.setVerticalTextPosition(JLabel.BOTTOM);
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        errorFrame.add(errorLabel);

        errorFrame.setVisible(true);
    }
}