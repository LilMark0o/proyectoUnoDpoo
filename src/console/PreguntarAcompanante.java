package console;

import javax.swing.*;

import serviciosPack.Huesped;
import serviciosPack.Servicios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreguntarAcompanante extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textDesde;
    private JTextField textNombre;
    private JTextField textEdad;
    private JButton cargarButton;

    public PreguntarAcompanante() {
        setTitle("Acompañante");
        setSize(400, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Acompañante Data", SwingConstants.CENTER);
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

        JLabel labelDesde = new JLabel("ID acompañante: ");
        textDesde = new JTextField();
        JLabel labelNombre = new JLabel("Nombre acompañante: ");
        textNombre = new JTextField();
        JLabel labelEdad = new JLabel("Edad acompañante: ");
        textEdad = new JTextField();

        panelContenedor.add(labelNombre);
        panelContenedor.add(textNombre);
        panelContenedor.add(labelEdad);
        panelContenedor.add(textEdad);
        panelContenedor.add(labelDesde);
        panelContenedor.add(textDesde);

        panelCentral.add(panelContenedor, BorderLayout.CENTER);
        // !
        add(panelCentral, BorderLayout.CENTER);

        // 3. Crear el panel inferior con GridLayout
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 3));
        // Aquí puedes agregar tus componentes al panel inferior
        // ? cargar button
        cargarButton = new JButton("Buscar");

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textNombre.getText();
                int edad = Integer.parseInt(textEdad.getText());
                String ID = textDesde.getText();
                Huesped persona = new Huesped(nombre, edad, ID, Servicios.IDGrupoActual);
                Servicios.personitasImportadas.add(persona);
                Servicios.segui = true;
                dispose();
            }
        });

        panelInferior.add(cargarButton);
        panelInferior.add(new JLabel(""));
        panelInferior.add(new JLabel(""));
        add(panelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }

}