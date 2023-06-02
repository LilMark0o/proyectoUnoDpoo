package bancosPack;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Pagos {
    public void aceptarPagoHabitacion(String titularTarjeta, String IdReservante, String numTarjeta, String desde,
            String hasta) {
    };

    public void hacerPagoGeneral(String titularTarjeta, String IdReservante, String numTarjeta, int cobroAHacer) {
    };

    public void prueba() {
        System.out.println("Sirvo!!!");
    }

    public void escribirFactura(String text, String fileName) throws IOException {
        String textoViejo = textoViejo(fileName);
        String textoActualizado = textoViejo + "\n" + text;
        String archivo = System.getProperty("user.dir") + "/data/bankingData/" + fileName;
        FileWriter writer = new FileWriter(archivo);
        writer.write(textoActualizado);
        writer.close();
    }

    public String textoViejo(String fileName) throws FileNotFoundException {
        String aMeterGrande = "";
        boolean primero = true;
        String archivo = System.getProperty("user.dir") + "/data/bankingData/" + fileName;
        File file = new File(archivo);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            if (primero) {
                primero = false;
            } else {
                aMeterGrande += "\n";
            }
            String linea = scan.nextLine();
            aMeterGrande += linea;

        }
        scan.close();
        return aMeterGrande;
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

    public void showErrorFrame(String text) {
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

    public void showInfoFrameLargo(String[] text) {
        JFrame errorFrame = new JFrame("Info");
        errorFrame.setBackground(Color.WHITE);
        errorFrame.setSize(400, 100);
        errorFrame.setLocationRelativeTo(null);

        JPanel textPanel = new JPanel();
        ImageIcon errorIcon = new ImageIcon("./data/images/info.png");
        JLabel errorLabel = new JLabel("", errorIcon, JLabel.CENTER);
        errorLabel.setVerticalTextPosition(JLabel.BOTTOM);
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        textPanel.add(errorLabel);
        JLabel vacioLabel = new JLabel("");
        vacioLabel.setVerticalTextPosition(JLabel.BOTTOM);
        vacioLabel.setHorizontalTextPosition(JLabel.CENTER);
        textPanel.add(vacioLabel);
        for (String texto : text) {
            texto += ", ";
            JLabel textoLabel = new JLabel(texto);
            textoLabel.setVerticalTextPosition(JLabel.BOTTOM);
            textoLabel.setHorizontalTextPosition(JLabel.CENTER);
            textPanel.add(textoLabel);
        }
        errorFrame.add(textPanel);
        errorFrame.setVisible(true);
    }

}