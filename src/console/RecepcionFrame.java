package console;

import javax.swing.*;

import controllerPack.Controller;
import serviciosPack.Servicios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecepcionFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton reservar;
    private JButton consultarTarifa;
    private JButton cancelarReserva;
    private JButton generarFactura;
    private JButton checkOut;
    private JButton mostrarGrafica;
    private JButton exitButton;

    public RecepcionFrame() {
        setTitle("Opciones Recepción");
        setSize(600, 300);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Opciones de servicios", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // 2. Crear el panel central con GridLayout
        JPanel panelCentral = new JPanel();

        // !
        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 pixels de espacio entre los componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de espacio en los bordes

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // GridLayout de 3 filas y 2 columnas
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los bordes

        // Crear los botones
        reservar = new JButton("Reservar");
        consultarTarifa = new JButton("Consultar Tarifas");
        cancelarReserva = new JButton("Cancelar Reserva ");
        generarFactura = new JButton("Generar Factura");
        checkOut = new JButton("Check-Out");
        mostrarGrafica = new JButton("Mostrar Gráfica");
        reservar.addActionListener(this);
        consultarTarifa.addActionListener(this);
        cancelarReserva.addActionListener(this);
        generarFactura.addActionListener(this);
        checkOut.addActionListener(this);
        mostrarGrafica.addActionListener(this);

        // Agregar los botones al panel
        buttonPanel.add(reservar);
        buttonPanel.add(consultarTarifa);
        buttonPanel.add(cancelarReserva);
        buttonPanel.add(generarFactura);
        buttonPanel.add(checkOut);
        buttonPanel.add(mostrarGrafica);

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
        if (e.getSource() == reservar) {
            if (Controller.devolverEmpleo().equals("recepcion")) {
                new HacerReserva();
            } else {
                CambiarTarifa.showErrorFrame("Solo recepcion tiene acceso");
            }
        } else if (e.getSource() == consultarTarifa) {
            if (Controller.devolverEmpleo().equals("recepcion")) {
                new ConsultarTarifa();
            } else {
                CambiarTarifa.showErrorFrame("Solo recepcion tiene acceso");
            }
        } else if (e.getSource() == cancelarReserva) {
            if (Controller.devolverEmpleo().equals("recepcion")) {
                new CancelarReserva();
            } else {
                CambiarTarifa.showErrorFrame("Solo recepcion tiene acceso");
            }
        } else if (e.getSource() == generarFactura) {
            if (Controller.devolverEmpleo().equals("recepcion")) {
                new GenerarFacturas();
            } else {
                CambiarTarifa.showErrorFrame("Solo recepcion tiene acceso");
            }
        } else if (e.getSource() == checkOut) {
            if (Controller.devolverEmpleo().equals("recepcion")) {
                new CheckOut();
            } else {
                CambiarTarifa.showErrorFrame("Solo recepcion tiene acceso");
            }
        } else if (e.getSource() == mostrarGrafica) {
            new GraficasMenu();
        }
    }

}