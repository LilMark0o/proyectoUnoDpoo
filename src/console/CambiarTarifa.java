package console;

import javax.swing.*;

import controllerPack.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarTarifa extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboTipo;
    private JTextField textDesde;
    private JTextField textHasta;
    private JTextField textDia;
    private JTextField textTarifa;
    private JButton exitButton;
    private JButton cargarButton;

    public CambiarTarifa() {
        setTitle("Cambiar tarifa");
        setSize(550, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Cambiar Tarifa", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // 2. Crear el panel central con GridLayout
        JPanel panelCentral = new JPanel();

        // !
        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 píxeles de espacio entre componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margen de 10 píxeles en cada borde

        JPanel panelContenedor = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas, 10 píxeles de espacio
                                                                           // entre
        // filas y columnas
        panelContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los
                                                                                    // bordes

        JLabel labelTipo = new JLabel("Tipo habitación: ");
        comboTipo = new JComboBox<>(new String[] { "estándar", "suite", "suite doble" });
        JLabel labelDesde = new JLabel("Desde que fecha: (ej: 2023-03-23)");
        textDesde = new JTextField();
        JLabel labelHasta = new JLabel("Hasta que fecha: (ej: 2023-04-04)");
        textHasta = new JTextField();
        JLabel labelDia = new JLabel("Qué día de la semana: (minúscula + tildes) ");
        textDia = new JTextField();
        JLabel labelTarifa = new JLabel("Tarifa a aplicar: ");
        textTarifa = new JTextField();

        panelContenedor.add(labelTipo);
        panelContenedor.add(comboTipo);
        panelContenedor.add(labelDesde);
        panelContenedor.add(textDesde);
        panelContenedor.add(labelHasta);
        panelContenedor.add(textHasta);
        panelContenedor.add(labelDia);
        panelContenedor.add(textDia);
        panelContenedor.add(labelTarifa);
        panelContenedor.add(textTarifa);

        panelCentral.add(panelContenedor, BorderLayout.CENTER);
        // !
        add(panelCentral, BorderLayout.CENTER);

        // 3. Crear el panel inferior con GridLayout
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 3));
        // Aquí puedes agregar tus componentes al panel inferior
        // ? cargar button
        cargarButton = new JButton("Cargar");

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboTipo.getSelectedItem();
                String desde = textDesde.getText();
                String hasta = textHasta.getText();
                String dia = textDia.getText();
                int tarifa = Integer.parseInt(textTarifa.getText());
                try {
                    String message = Controller.cambiarTarifa(tipo, desde, hasta, dia, tarifa);
                    showSuccessFrame(message);
                } catch (Exception en) {
                    showErrorFrame("Hubo un error");
                }
            }
        });

        // ? salir button
        exitButton = new JButton("Atrás");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelInferior.add(cargarButton);
        panelInferior.add(new JLabel(""));
        panelInferior.add(exitButton);
        add(panelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void showSuccessFrame(String text) {
        JFrame successFrame = new JFrame("Éxito");
        successFrame.setBackground(Color.WHITE);
        successFrame.setSize(300, 200);
        successFrame.setLocationRelativeTo(null);

        ImageIcon successIcon = new ImageIcon("./data/images/check.png");
        JLabel successLabel = new JLabel(text, successIcon, JLabel.CENTER);
        successLabel.setVerticalTextPosition(JLabel.BOTTOM);
        successLabel.setHorizontalTextPosition(JLabel.CENTER);
        successFrame.add(successLabel);

        successFrame.setVisible(true);
    }

    public static void showErrorFrame(String text) {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setBackground(Color.WHITE);
        errorFrame.setSize(300, 200);
        errorFrame.setLocationRelativeTo(null);

        ImageIcon errorIcon = new ImageIcon("./data/images/error.png");
        JLabel errorLabel = new JLabel(text, errorIcon, JLabel.CENTER);
        errorLabel.setVerticalTextPosition(JLabel.BOTTOM);
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        errorFrame.add(errorLabel);

        errorFrame.setVisible(true);
    }

}