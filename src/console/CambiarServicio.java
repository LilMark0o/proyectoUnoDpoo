package console;

import javax.swing.*;

import controllerPack.Controller;
import inventarioPack.Inventario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CambiarServicio extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboCantidadPersonas;
    private JTextField textElemento;
    private JTextField textPrecio;
    private JButton exitButton;
    private JButton cargarButton;

    public CambiarServicio() {
        setTitle("Cargar menu");
        setSize(1000, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Cargar menu", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // ! ACÁ EMPIEZA LA PARTE RARITA Y EXPERIMENTAL

        JPanel grandeMitad = new JPanel();
        grandeMitad.setLayout(new GridLayout(1, 2));
        String menuText = Controller.mostrarServicios();
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

        JLabel labelElemento = new JLabel("Elemento a cambiar: ");
        textElemento = new JTextField();
        JLabel labelCantidadPersonas = new JLabel("Cuantas personas: ");
        comboCantidadPersonas = new JComboBox<>(new String[] { "Grupal", "Personal" });
        JLabel labelPrecio = new JLabel("Precio a aplicar: ");
        textPrecio = new JTextField();

        panelContenedor.add(labelElemento);
        panelContenedor.add(textElemento);
        panelContenedor.add(labelCantidadPersonas);
        panelContenedor.add(comboCantidadPersonas);
        panelContenedor.add(labelPrecio);
        panelContenedor.add(textPrecio);

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
                String Elemento = textElemento.getText();
                String CantidadPersonas = (String) comboCantidadPersonas.getSelectedItem();
                int Precio = Integer.parseInt(textPrecio.getText());
                try {
                    Controller.cambiarServicios(Elemento, CantidadPersonas, Precio);
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