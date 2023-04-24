package console;

import javax.swing.*;

import controllerPack.Controller;
import inventarioPack.Inventario;
import serviciosPack.Servicios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegistrarServicioRestaurante extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboUsuarioPago;
    private JTextField textNombreServicio;
    private JTextField textID;
    private JButton exitButton;
    private JButton cargarButton;

    public RegistrarServicioRestaurante() {
        setTitle("Registrar Servicio Restaurante");
        setSize(1000, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Registrar Servicio Restaurante", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // ! ACÁ EMPIEZA LA PARTE RARITA Y EXPERIMENTAL

        JPanel grandeMitad = new JPanel();
        grandeMitad.setLayout(new GridLayout(1, 2));
        String menuText = Controller.mostrarMenu();
        String[] partes = menuText.split("\n");

        JPanel mitadDerechaTexto = new JPanel();
        mitadDerechaTexto.setLayout(new GridLayout(partes.length, 1));
        for (String frase : partes) {
            JLabel textito = new JLabel(frase);
            mitadDerechaTexto.add(textito);
        }
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

        JLabel labelID = new JLabel("ID a cargar (Habitación o Persona): ");
        textID = new JTextField();
        JLabel labelNombreServicio = new JLabel("Nombre del producto del menú: ");
        textNombreServicio = new JTextField();
        JLabel labelUsuarioPago = new JLabel("Usuario pagó: ");
        comboUsuarioPago = new JComboBox<>(new String[] { "Sí", "No" });

        panelContenedor.add(labelID);
        panelContenedor.add(textID);
        panelContenedor.add(labelNombreServicio);
        panelContenedor.add(textNombreServicio);
        panelContenedor.add(labelUsuarioPago);
        panelContenedor.add(comboUsuarioPago);

        panelCentral.add(panelContenedor, BorderLayout.CENTER);
        // !
        grandeMitad.add(panelCentral);
        grandeMitad.add(mitadDerechaTexto);
        add(grandeMitad, BorderLayout.CENTER);

        // 3. Crear el panel inferior con GridLayout
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 3));
        // Aquí puedes agregar tus componentes al panel inferior
        // ? cargar button
        cargarButton = new JButton("Cargar");

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = textID.getText();
                String NombreServicio = textNombreServicio.getText();
                String UsuarioPago = (String) comboUsuarioPago.getSelectedItem();
                try {
                    Controller.cargarServicioRestaurante(ID, NombreServicio, UsuarioPago);
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
                try {
                    Inventario.guardarCambios();
                    Servicios.guardarCambios();
                } catch (IOException e1) {
                }
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