package console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton cargarHabitacionArc;
    private JButton cambiarTarifa;
    private JButton cargarHabitacionInd;
    private JButton cambiarMenu;
    private JButton consultaHabitacion;
    private JButton cambioServicio;
    private JButton exitButton;

    public InventarioFrame() {
        setTitle("Opciones Inventario");
        setSize(600, 300);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Opciones de inventario", SwingConstants.CENTER);
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
        cargarHabitacionArc = new JButton("Cargar habitación (archivo)");
        cambiarTarifa = new JButton("Cambiar tarifa");
        cargarHabitacionInd = new JButton("Cargar habitación (individual)");
        cambiarMenu = new JButton("Cambiar menu");
        consultaHabitacion = new JButton("Consultar habitación (por ID)");
        cambioServicio = new JButton("Cambiar servicio");
        cargarHabitacionArc.addActionListener(this);
        cambiarTarifa.addActionListener(this);
        cargarHabitacionInd.addActionListener(this);
        cambiarMenu.addActionListener(this);
        consultaHabitacion.addActionListener(this);

        // Agregar los botones al panel
        buttonPanel.add(cargarHabitacionArc);
        buttonPanel.add(cambiarTarifa);
        buttonPanel.add(cargarHabitacionInd);
        buttonPanel.add(cambiarMenu);
        buttonPanel.add(consultaHabitacion);
        buttonPanel.add(cambioServicio);

        // Agregar el panel de botones al panel principal
        panelCentral.add(buttonPanel, BorderLayout.CENTER);
        // !
        // Aquí puedes agregar tus componentes al panel central
        // Ejemplo:
        // llenarMitad(panelCentral);
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
        if (e.getSource() == cargarHabitacionArc) {
            System.out.println("HOLA, sirvo");
        } else if (e.getSource() == cambiarTarifa) {
            new CambiarTarifa();
        } else if (e.getSource() == cargarHabitacionInd) {
            System.out.println("HOLA, sirvo3");
        } else if (e.getSource() == cambiarMenu) {
            System.out.println("HOLA, sirvo3");
        } else if (e.getSource() == consultaHabitacion) {
            System.out.println("HOLA, sirvo3");
        } else if (e.getSource() == cambioServicio) {
            System.out.println("HOLA, sirvo3");
        }
    }

}