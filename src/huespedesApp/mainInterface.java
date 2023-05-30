package huespedesApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import controllerPack.Controller;

public class mainInterface extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static Boolean logedIn = false;
    private static JButton OpNuevoUsuario;
    private static JButton OpLogIn;
    private static JButton OpFuncionalidades;

    public mainInterface() {
        try {
            Controller.startApp();
        } catch (FileNotFoundException e) {
        }

        setTitle("HUÉSPEDS");
        setSize(1000, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ! parte de arriba lista
        JPanel arriba = new JPanel();
        arriba.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 249), 3));
        arriba.setLayout(new GridLayout(1, 1));
        arriba.setPreferredSize(new Dimension(getWidth(), 105));
        JLabel titulo = new JLabel("G-TEL HUÉSPEDS APP");
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        arriba.add(titulo);
        arriba.setBackground(Color.WHITE);
        add(arriba, BorderLayout.NORTH);

        // ?
        // voy a experimentar
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);

        panelCentral.setLayout(new BorderLayout(10, 10)); // 10 pixels de espacio entre los componentes
        panelCentral.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60)); // 20 pixels de espacio en los bordes

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 30, 10)); // GridLayout de 3 filas y 2 columnas
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espacio en los bordes

        // Crear los botones
        OpNuevoUsuario = new JButton("Crear usuario");
        OpNuevoUsuario.setBackground(Color.WHITE);
        OpLogIn = new JButton("Autenticar usuario");
        OpLogIn.setBackground(Color.WHITE);
        OpFuncionalidades = new JButton("Funcionalidades Usuario");
        OpFuncionalidades.setBackground(Color.WHITE);

        // se pone el actionListener
        OpNuevoUsuario.addActionListener(this);
        OpLogIn.addActionListener(this);
        OpFuncionalidades.addActionListener(this);

        // se colocan enabled
        if (!logedIn) {
            OpFuncionalidades.setEnabled(logedIn);
        }

        // Agregar los botones al panel
        buttonPanel.add(OpNuevoUsuario);
        buttonPanel.add(OpLogIn);
        buttonPanel.add(OpFuncionalidades);

        // Agregar el panel de botones al panel principal
        panelCentral.add(buttonPanel, BorderLayout.CENTER);
        // Aquí puedes agregar tus componentes al panel central
        // Ejemplo:
        // llenarMitad(panelCentral);
        add(panelCentral, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        mainInterface ventana = new mainInterface();
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OpNuevoUsuario) {
            new CrearUsuario();
        } else if (e.getSource() == OpLogIn) {
            new AutenticarUsuario();
        } else if (e.getSource() == OpFuncionalidades) {
            new OpcionesHuesped();
        }
    }

    public static void actualizarEstadoBotones() {
        OpFuncionalidades.setEnabled(logedIn);
    }

}
