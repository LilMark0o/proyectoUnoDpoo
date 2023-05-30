package huespedesApp;

import javax.swing.*;

import console.HacerReserva;
import controllerPack.Controller;
import inventarioPack.Inventario;
import serviciosPack.Servicios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpcionesHuesped extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton ConsultarHabitacion;
    private JButton ReservarHabitacion;
    private JButton exitButton;

    public OpcionesHuesped() {
        setTitle("Opciones Huesped");
        setSize(300, 250);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Opciones Huesped", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // 2. Crear el panel central con GridLayout
        JPanel panelCentral = new JPanel();

        // !
        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 pixels de espacio entre los componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de espacio en los bordes

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // GridLayout de 3 filas y 2 columnas
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los bordes

        // Crear los botones
        ConsultarHabitacion = new JButton("Habitaciones disponibles");
        ReservarHabitacion = new JButton("Hacer reserva");
        ConsultarHabitacion.addActionListener(this);
        ReservarHabitacion.addActionListener(this);

        // Agregar los botones al panel
        buttonPanel.add(ConsultarHabitacion);
        buttonPanel.add(ReservarHabitacion);

        panelCentral.add(buttonPanel, BorderLayout.CENTER);
        // !
        add(panelCentral, BorderLayout.CENTER);

        // 3. Crear el panel inferior con GridLayout
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 3));
        // Aquí puedes agregar tus componentes al panel inferior
        // Ejemplo:
        exitButton = new JButton("Atrás");
        panelInferior.add(new JLabel(""));

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
        panelInferior.add(exitButton);
        panelInferior.add(new JLabel(""));
        add(panelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ConsultarHabitacion) {
            new HabitacionDisponible();
        } else if (e.getSource() == ReservarHabitacion) {
            HacerReserva a = new HacerReserva();
            String correo = Controller.devolverCorreo();
            a.realizadoPorUnHuesped = true;
            a.actualizarCorreo(correo);
            a.actualizarHabitaciones();
        }
    }

}