package console;

import javax.swing.*;

import controllerPack.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HacerReserva extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboTipo;
    private JTextField textIDReservante;
    private JTextField textDesde;
    private JTextField textHasta;
    private JTextField textIDNombre;
    private JTextField textIDEdad;

    private JTextField textCorreoReservante;
    private JTextField textAcompanantes;
    private JTextField textCelularReservante;
    private JButton exitButton;
    private JButton cargarButton;

    public HacerReserva() {
        setTitle("Hacer Reserva");
        setSize(500, 550);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Hacer Reserva", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // 2. Crear el panel central con GridLayout
        JPanel panelCentral = new JPanel();

        // !
        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 píxeles de espacio entre componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margen de 10 píxeles en cada borde

        JPanel panelContenedor = new JPanel(new GridLayout(9, 2, 10, 10)); // 5 filas, 2 columnas, 10 píxeles de espacio
                                                                           // entre
        // filas y columnas
        panelContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los
                                                                                    // bordes

        JLabel labelTipo = new JLabel("Tipo habitación: ");
        comboTipo = new JComboBox<>(new String[] { "estándar", "suite", "suite doble" });
        JLabel labelIDReservante = new JLabel("ID reservanter: ");
        textIDReservante = new JTextField();
        JLabel labelIDNombre = new JLabel("Nombre Reservante: ");
        textIDNombre = new JTextField();
        JLabel labelIDEdad = new JLabel("Edad reservante: ");
        textIDEdad = new JTextField();
        JLabel labelCorreoReservante = new JLabel("Correo Reservante: ");
        textCorreoReservante = new JTextField();
        JLabel labelAcompanantes = new JLabel("Acompañantes: ");
        textAcompanantes = new JTextField();
        JLabel labelCelularReservante = new JLabel("Celular del reservante: ");
        textCelularReservante = new JTextField();
        JLabel labelDesde = new JLabel("Desde que fecha: (ej: 2023-03-23)");
        textDesde = new JTextField();
        JLabel labelHasta = new JLabel("Hasta que fecha: (ej: 2023-04-04)");
        textHasta = new JTextField();

        panelContenedor.add(labelIDNombre);
        panelContenedor.add(textIDNombre);
        panelContenedor.add(labelIDEdad);
        panelContenedor.add(textIDEdad);
        panelContenedor.add(labelIDReservante);
        panelContenedor.add(textIDReservante);
        panelContenedor.add(labelCorreoReservante);
        panelContenedor.add(textCorreoReservante);
        panelContenedor.add(labelCelularReservante);
        panelContenedor.add(textCelularReservante);
        panelContenedor.add(labelAcompanantes);
        panelContenedor.add(textAcompanantes);
        //
        panelContenedor.add(labelDesde);
        panelContenedor.add(textDesde);
        panelContenedor.add(labelHasta);
        panelContenedor.add(textHasta);
        panelContenedor.add(labelTipo);
        panelContenedor.add(comboTipo);
        //

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
                String NombreReservante = textIDNombre.getText();
                int edadReservante = Integer.parseInt(textIDEdad.getText());
                String IDReservante = textIDReservante.getText();
                String CorreoReservante = textCorreoReservante.getText();
                Long CelularReservante = Long.parseLong(textCelularReservante.getText());
                int Acompanantes = Integer.parseInt(textAcompanantes.getText());
                String desde = textDesde.getText();
                String hasta = textHasta.getText();
                String tipo = (String) comboTipo.getSelectedItem();
                System.out.println(Acompanantes);
                if (Acompanantes == 0) {
                    System.out.println("Error");
                }

                Controller.generarReserva(NombreReservante, edadReservante, IDReservante,
                        CorreoReservante, CelularReservante, Acompanantes, desde, hasta, tipo);
                CambiarTarifa.showSuccessFrame("Ponga los acompañantes y su reserva estará lista");
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
}