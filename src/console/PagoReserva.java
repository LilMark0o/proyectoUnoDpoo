package console;

import javax.swing.*;

import bancosPack.Pagos;
import controllerPack.Controller;
import loginApp.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PagoReserva extends JFrame {
    private static final long serialVersionUID = 1L;

    private String idReservante;
    private String nombreReservante;
    private String fechaInicio;
    private String fechaFin;
    public int cobroAHacer = 0;

    private JTextField numeroTarjetaField;
    private JTextField nombreTitularField;
    private JComboBox<String> comboPasarelas;
    private JButton pagarButton;
    private JButton exitButton;

    private String numTarjeta;
    private String titularTarjeta;

    public PagoReserva(String nombreReservante, String idReservante, String fechaInicio, String fechaFin) {
        this.idReservante = idReservante;
        this.nombreReservante = nombreReservante;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        setTitle("Pagar Reserva");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() {
        numeroTarjetaField = new JTextField(15);
        nombreTitularField = new JTextField(15);
        nombreTitularField.setText(nombreReservante);
        comboPasarelas = new JComboBox<>(new String[] { "no hay pasarelas disponibles" });
        try {
            actualizarPasarelas();
        } catch (FileNotFoundException e) {
        }

        pagarButton = new JButton("Pagar ");

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numTarjeta = numeroTarjetaField.getText();
                titularTarjeta = nombreTitularField.getText();
                String plataforma = (String) comboPasarelas.getSelectedItem();
                if (plataforma != "") {
                    try {
                        Class<?> cls = Class.forName("bancosPack." + plataforma);
                        // Pagos obj = (Pagos) cls.newInstance();
                        Pagos obj = (Pagos) cls.getDeclaredConstructor().newInstance();
                        if (cobroAHacer == 0) {
                            obj.aceptarPagoHabitacion(titularTarjeta, idReservante, numTarjeta,
                                    fechaInicio, fechaFin);
                        } else {
                            obj.hacerPagoGeneral(titularTarjeta, idReservante, numTarjeta,
                                    cobroAHacer);
                        }
                        dispose();

                    } catch (ClassNotFoundException e1) {
                    } catch (InstantiationException e1) {
                    } catch (IllegalAccessException e1) {
                    } catch (IllegalArgumentException e1) {
                    } catch (InvocationTargetException e1) {
                    } catch (NoSuchMethodException e1) {
                    } catch (SecurityException e1) {
                    }
                } else {
                    showErrorFrame();
                }
                mainInterface.actualizarEstadoBotones();

            }
        });
        exitButton = new JButton("Atrás");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Login.guardarCambios();
                } catch (IOException e1) {
                }
                showInfoFrame();
                Controller.cargarServicioHabitacion(idReservante, "No", fechaInicio, fechaFin);
                dispose();
            }
        });
    }

    private void addComponentsToFrame() {
        add(new JLabel(""));
        add(new JLabel(""));
        JLabel nombreTitularLabel = new JLabel("Titular de la tarjeta:");
        nombreTitularLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nombreTitularLabel);

        add(nombreTitularField);

        JLabel numeroTarjetaLabel = new JLabel("Número tarjeta:");
        numeroTarjetaLabel.setHorizontalAlignment(JLabel.CENTER);
        add(numeroTarjetaLabel);

        add(numeroTarjetaField);

        JLabel bancoLabel = new JLabel("Banco asociado:");
        bancoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(bancoLabel);

        add(comboPasarelas);
        add(new JLabel(""));
        add(new JLabel(""));
        add(pagarButton);
        add(exitButton);
    }

    private void showErrorFrame() {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setBackground(Color.WHITE);
        errorFrame.setSize(300, 200);
        errorFrame.setLocationRelativeTo(null);

        ImageIcon errorIcon = new ImageIcon("./data/images/error.png");
        JLabel errorLabel = new JLabel("Error: usuario o contraseña invalida.", errorIcon, JLabel.CENTER);
        errorLabel.setVerticalTextPosition(JLabel.BOTTOM);
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        errorFrame.add(errorLabel);

        errorFrame.setVisible(true);
    }

    private void showInfoFrame() {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setBackground(Color.WHITE);
        errorFrame.setSize(300, 200);
        errorFrame.setLocationRelativeTo(null);

        ImageIcon errorIcon = new ImageIcon("./data/images/info.png");
        JLabel errorLabel = new JLabel("Deberás pagar la reserva luego.", errorIcon, JLabel.CENTER);
        errorLabel.setVerticalTextPosition(JLabel.BOTTOM);
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        errorFrame.add(errorLabel);

        errorFrame.setVisible(true);
    }

    private void actualizarPasarelas() throws FileNotFoundException {
        ArrayList<String> plataformasDisponibles = Controller.leerPlataformas();
        DefaultComboBoxModel<String> modelActualizado = new DefaultComboBoxModel<>();
        for (String pasarela : plataformasDisponibles) {
            modelActualizado.addElement(pasarela);
        }
        comboPasarelas.setModel(modelActualizado);
    }
}
