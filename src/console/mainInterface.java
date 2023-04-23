package console;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import controllerPack.Controller;
import inventarioPack.Inventario;
import loginApp.Login;
import serviciosPack.Servicios;

public class mainInterface extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static Boolean logedIn = true;
    private static JLabel buttonProfile;
    private static JButton buttonLogIn;
    private static JButton buttonLogOut;
    private static JButton buttonExit;
    private static JButton OpInventario;
    private static JButton OpServicio;
    private static JButton OpRecepcion;

    public mainInterface() {
        try {
            Controller.startApp();
        } catch (FileNotFoundException e) {
        }

        setTitle("G-TEL PROPERTY MANAGMENT SYSTEM");
        setSize(1000, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // AcÃ¡ sirve el dispose on close,
        // sirve para guardar los cambos

        setLayout(new BorderLayout());

        // ! parte de arriba lista
        JPanel arriba = new JPanel();
        arriba.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 249), 3));
        arriba.setLayout(new GridLayout(1, 1));
        JLabel emptyText = new JLabel("");
        arriba.add(emptyText);
        arriba.setPreferredSize(new Dimension(getWidth(), 105));
        JLabel titulo = new JLabel("G - T E L  P R O P E R T Y  M A N A G M EN T   S Y S T E M");
        titulo.setFont(new Font("Serif", Font.PLAIN, 30));
        titulo.setForeground(new Color(48, 48, 48));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        arriba.add(titulo);
        arriba.setBackground(Color.WHITE);
        add(arriba, BorderLayout.NORTH);

        // ! parte de abajo lista
        JPanel abajo = new JPanel();
        abajo.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 249), 3));

        abajo.setLayout(new GridLayout(1, 9));
        abajo.setPreferredSize(new Dimension(getWidth(), 105));
        buttonProfile = new JLabel();
        buttonProfile.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        ImageIcon image = new ImageIcon("./data/images/user.png");
        buttonProfile.setIcon(image);
        buttonProfile.setHorizontalAlignment(JLabel.CENTER);
        buttonLogIn = new JButton();
        buttonLogIn.setText("Log-in");
        buttonLogIn.addActionListener(this);
        buttonLogOut = new JButton();
        buttonLogOut.addActionListener(this);
        buttonLogOut.setText("Log-out");
        if (!logedIn) {
            buttonLogOut.setEnabled(false);
        }
        buttonExit = new JButton();
        buttonExit.setText("Salir");
        buttonExit.addActionListener(this);

        abajo.add(buttonProfile);
        abajo.add(new JLabel(""));
        abajo.add(buttonLogIn);
        abajo.add(new JLabel(""));
        abajo.add(buttonLogOut);
        abajo.add(emptyText);
        abajo.add(buttonExit);
        abajo.setBackground(Color.WHITE);
        add(abajo, BorderLayout.SOUTH);

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
        OpInventario = new JButton("Inventario Options");
        OpInventario.setBackground(Color.WHITE);
        OpServicio = new JButton("Servicios Options");
        OpServicio.setBackground(Color.WHITE);
        OpRecepcion = new JButton("Recepción Option");
        OpRecepcion.setBackground(Color.WHITE);

        // se pone el actionListener
        OpInventario.addActionListener(this);
        OpServicio.addActionListener(this);
        OpRecepcion.addActionListener(this);

        // se colocan enabled
        if (!logedIn) {
            OpInventario.setEnabled(logedIn);
        }
        if (!logedIn) {
            OpServicio.setEnabled(logedIn);
        }
        if (!logedIn) {
            OpRecepcion.setEnabled(logedIn);
        }

        // Agregar los botones al panel
        buttonPanel.add(OpInventario);
        buttonPanel.add(OpServicio);
        buttonPanel.add(OpRecepcion);

        // Agregar el panel de botones al panel principal
        panelCentral.add(buttonPanel, BorderLayout.CENTER);
        // Aquí puedes agregar tus componentes al panel central
        // Ejemplo:
        // llenarMitad(panelCentral);
        add(panelCentral, BorderLayout.CENTER);

        // ?

        // ! forma que sirve
        // JPanel centro = new JPanel(new GridLayout(3, 1));
        // centro.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 249),
        // 3));
        // JPanel vacio1 = new JPanel();
        // vacio1.setBackground(Color.white);
        // JPanel botones = new JPanel(new GridLayout(1, 5));
        // JPanel vacioUno = new JPanel();
        // vacioUno.setBackground(Color.white);
        // JPanel vacioDos = new JPanel();
        // vacioDos.setBackground(Color.white);
        // JPanel vacioTres = new JPanel();
        // vacioTres.setBackground(Color.white);
        // JPanel vacioCuatro = new JPanel();
        // vacioCuatro.setBackground(Color.white);
        // OpInventario = new JButton();
        // OpInventario.setText("Inventario Options");
        // OpInventario.addActionListener(this);
        // OpServicio = new JButton();
        // OpServicio.setText("Servicios Options");
        // OpServicio.addActionListener(this);
        // OpRecepcion = new JButton();
        // OpRecepcion.setText("Recepción Option");
        // if (!logedIn) {
        // OpInventario.setEnabled(false);
        // }
        // if (!logedIn) {
        // OpServicio.setEnabled(false);
        // }
        // if (!logedIn) {
        // OpRecepcion.setEnabled(false);
        // }
        // OpRecepcion.addActionListener(this);
        // botones.add(vacioUno);
        // botones.add(OpInventario);
        // botones.add(vacioDos);
        // botones.add(OpServicio);
        // botones.add(vacioTres);
        // botones.add(OpRecepcion);
        // botones.add(vacioCuatro);
        // JPanel vacio2 = new JPanel();
        // vacio2.setBackground(Color.white);
        // centro.add(vacio1);
        // centro.add(botones);
        // centro.add(vacio2);
        // add(centro, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        mainInterface ventana = new mainInterface();
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogIn) {
            new LogInLogOut();
        } else if (e.getSource() == buttonLogOut) {
            logedIn = false;
            Controller.logOut();
            actualizarEstadoBotones();
        } else if (e.getSource() == buttonExit) {
            try {
                Login.guardarCambios();
                Inventario.guardarCambios();
                Servicios.guardarCambios();
            } catch (IOException e1) {
            }
            dispose();
        } else if (e.getSource() == OpInventario) {
            new InventarioFrame();
        } else if (e.getSource() == OpServicio) {
            new ServiciosFrame();
        } else if (e.getSource() == OpRecepcion) {
            new RecepcionFrame();
        }
    }

    public static void actualizarEstadoBotones() {
        buttonLogOut.setEnabled(logedIn);
        OpInventario.setEnabled(logedIn);
        OpServicio.setEnabled(logedIn);
        OpRecepcion.setEnabled(logedIn);
    }

}
