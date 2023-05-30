package huespedesApp;

import javax.swing.*;

import inventarioPack.Habitacion;
import inventarioPack.Inventario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HabitacionDisponible extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textDesde;
    private JTextField textHasta;
    private JButton exitButton;
    private JButton cargarButton;

    public HabitacionDisponible() {
        setTitle("Consultar habitaciones");
        setSize(550, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Consultar habitaciones", SwingConstants.CENTER);
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

        JLabel labelDesde = new JLabel("Desde que fecha: (ej: 2023-03-23)");
        textDesde = new JTextField();
        JLabel labelHasta = new JLabel("Hasta que fecha: (ej: 2023-04-04)");
        textHasta = new JTextField();

        panelContenedor.add(labelDesde);
        panelContenedor.add(textDesde);
        panelContenedor.add(labelHasta);
        panelContenedor.add(textHasta);

        panelCentral.add(panelContenedor, BorderLayout.CENTER);
        // !
        add(panelCentral, BorderLayout.CENTER);

        // 3. Crear el panel inferior con GridLayout
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 3));
        // Aquí puedes agregar tus componentes al panel inferior
        // ? cargar button
        cargarButton = new JButton("Consultar");

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desde = textDesde.getText();
                String hasta = textHasta.getText();
                try {
                    ArrayList<String> IDs = Inventario.queHabitacionesHay(desde, hasta);
                    if (IDs.size() == 0) {
                        showErrorFrame("NO hay habitaciones disponibles para estas fechas");
                    } else {
                        showInfoFrameLargo(IDs);
                    }
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

    public static void showInfoFrameLargo(ArrayList<String> text) {
        JFrame errorFrame = new JFrame("Info");
        errorFrame.setBackground(Color.WHITE);
        errorFrame.setSize(400, 400);
        errorFrame.setLocationRelativeTo(null);

        JPanel textPanel = new JPanel();
        ImageIcon errorIcon = new ImageIcon("./data/images/info.png");
        JLabel errorLabel = new JLabel("", errorIcon, JLabel.CENTER);
        errorLabel.setVerticalTextPosition(JLabel.BOTTOM);
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        textPanel.add(errorLabel);
        JLabel vacioLabel = new JLabel("");
        vacioLabel.setVerticalTextPosition(JLabel.BOTTOM);
        vacioLabel.setHorizontalTextPosition(JLabel.CENTER);
        textPanel.add(vacioLabel);
        JLabel tituloLabel = new JLabel("Las habitaciones disponibles son: ");
        tituloLabel.setVerticalTextPosition(JLabel.BOTTOM);
        tituloLabel.setHorizontalTextPosition(JLabel.CENTER);
        textPanel.add(tituloLabel);

        for (String idHabitacion : text) {
            String texto = "";
            Habitacion habitacion = Inventario.habitacionPorID(idHabitacion);
            texto += "la habitación de tipo " + habitacion.getTipoHabitacion() + " con ID: " + idHabitacion;
            JLabel textoLabel = new JLabel(texto);
            textoLabel.setVerticalTextPosition(JLabel.BOTTOM);
            textoLabel.setHorizontalTextPosition(JLabel.CENTER);
            textPanel.add(textoLabel);
        }
        errorFrame.add(textPanel);
        errorFrame.setVisible(true);
    }

}