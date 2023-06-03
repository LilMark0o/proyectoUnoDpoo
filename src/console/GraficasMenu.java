package console;

import javax.swing.*;

import inventarioPack.Inventario;
import serviciosPack.Servicios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GraficasMenu extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton GraficaReservasReservantes;
    private JButton RegistarServicioRestaurante;
    private JButton PagoServicio;
    private JButton ALGOOO;
    private JButton exitButton;

    public GraficasMenu() {
        setTitle("Opciones Indicadores");
        setSize(300, 350);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. Crear el título centrado

        JLabel titulo = new JLabel("Opciones Indicadores", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));

        add(titulo, BorderLayout.NORTH);

        // 2. Crear el panel central con GridLayout
        JPanel panelCentral = new JPanel();

        // !
        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 pixels de espacio entre los componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de espacio en los bordes

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // GridLayout de 3 filas y 2 columnas
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los bordes

        // Crear los botones
        GraficaReservasReservantes = new JButton("Gráfica Reservantes y reservas");
        RegistarServicioRestaurante = new JButton("Gráfica Servicios/Precio");
        PagoServicio = new JButton("Gráfica Servicios/Cantidad");
        ALGOOO = new JButton("Indicador Huespeds");
        GraficaReservasReservantes.addActionListener(this);
        RegistarServicioRestaurante.addActionListener(this);
        PagoServicio.addActionListener(this);
        ALGOOO.addActionListener(this);

        // Agregar los botones al panel
        buttonPanel.add(GraficaReservasReservantes);
        buttonPanel.add(RegistarServicioRestaurante);
        buttonPanel.add(PagoServicio);
        buttonPanel.add(ALGOOO);

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
        if (e.getSource() == GraficaReservasReservantes) {
            new DiagramaDeBarras();
        } else if (e.getSource() == RegistarServicioRestaurante) {
            new GraficaServicios();
        } else if (e.getSource() == PagoServicio) {
            new GraficaServiciosCantidad();
        } else if (e.getSource() == ALGOOO) {
            try {
                Servicios.escribirIndicador();
                showSuccessFrame("¡Hecho!, Revise la carpeta indicadores");
            } catch (IOException e1) {
            }
        }
    }

    public void showSuccessFrame(String text) {
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

}