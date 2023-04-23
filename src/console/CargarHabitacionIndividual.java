package console;

import javax.swing.*;

import controllerPack.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class CargarHabitacionIndividual extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboBalcon;
    private JComboBox<String> comboVista;
    private JComboBox<String> comboCocina;
    private JTextField textIDHabitacion;
    private JTextField textUbicacion;
    private JTextField textCapacidadAdulto;
    private JTextField textCapacidadNinos;
    private JButton exitButton;
    private JButton cargarButton;

    public CargarHabitacionIndividual() {
        setTitle("Cargar habitación(individual)");
        setSize(400, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Cargar habitación(individual)", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // 2. Crear el panel central con GridLayout
        JPanel panelCentral = new JPanel();

        // !
        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 píxeles de espacio entre componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margen de 10 píxeles en cada borde

        JPanel panelContenedor = new JPanel(new GridLayout(8, 2, 10, 10)); // 5 filas, 2 columnas, 10 píxeles de espacio
                                                                           // entre
        // filas y columnas
        panelContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los
                                                                                    // bordes

        JLabel labelTipo = new JLabel("Tipo habitación: ");
        comboTipo = new JComboBox<>(new String[] { "estándar", "suite", "suite doble" });
        JLabel labelIDHabitacion = new JLabel("ID habitación: ");
        textIDHabitacion = new JTextField();
        JLabel labelUbicacion = new JLabel("Ubicación habitación: ");
        textUbicacion = new JTextField();
        JLabel labelBalcon = new JLabel("Balcon: ");
        comboBalcon = new JComboBox<>(new String[] { "Sí", "No" });
        JLabel labelVista = new JLabel("Vista: ");
        comboVista = new JComboBox<>(new String[] { "Sí", "No" });
        JLabel labelCocina = new JLabel("Cocina: ");
        comboCocina = new JComboBox<>(new String[] { "Sí", "No" });
        JLabel labelCapacidadAdulto = new JLabel("Capacidad de adultos: ");
        textCapacidadAdulto = new JTextField();
        JLabel labelCapacidadNinos = new JLabel("Capacidad de niños: ");
        textCapacidadNinos = new JTextField();

        panelContenedor.add(labelTipo);
        panelContenedor.add(comboTipo);
        panelContenedor.add(labelIDHabitacion);
        panelContenedor.add(textIDHabitacion);
        panelContenedor.add(labelUbicacion);
        panelContenedor.add(textUbicacion);
        //
        panelContenedor.add(labelBalcon);
        panelContenedor.add(comboBalcon);
        panelContenedor.add(labelVista);
        panelContenedor.add(comboVista);
        panelContenedor.add(labelCocina);
        panelContenedor.add(comboCocina);
        //
        panelContenedor.add(labelCapacidadAdulto);
        panelContenedor.add(textCapacidadAdulto);
        panelContenedor.add(labelCapacidadNinos);
        panelContenedor.add(textCapacidadNinos);

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
                String IDHabitacion = textIDHabitacion.getText();
                String Ubicacion = textUbicacion.getText();
                String balcon = (String) comboBalcon.getSelectedItem();
                String vista = (String) comboVista.getSelectedItem();
                String cocina = (String) comboCocina.getSelectedItem();
                int CapacidadAdulto = Integer.parseInt(textCapacidadAdulto.getText());
                int CapacidadNinos = Integer.parseInt(textCapacidadNinos.getText());
                try {
                    String message = Controller.cargarHabitacionManual(tipo, IDHabitacion, Ubicacion, balcon, vista,
                            cocina, CapacidadAdulto, CapacidadNinos);
                    if (message.equals("El ID dado ya existe, vuelva a intentarlo con un ID nuevo")) {
                        showErrorFrame("El ID ya existe, debe cambiarlo");
                    } else {
                        showSuccessFrame("¡Habitación cargada exitosamente!");
                    }
                } catch (FileNotFoundException e1) {
                    showErrorFrame("Hubo un error, vuelva a intentarlo");
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

    private void showSuccessFrame(String text) {
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

    private void showErrorFrame(String text) {
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