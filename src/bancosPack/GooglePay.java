package bancosPack;

import java.io.IOException;

import controllerPack.Controller;
import serviciosPack.Servicios;

public class GooglePay extends Pagos {
    @Override
    public void aceptarPagoHabitacion(String titularTarjeta, String IdReservante, String numTarjeta, String desde,
            String hasta) {
        int costo = Controller.cargarServicioHabitacion(IdReservante, "Sí", desde, hasta);
        // ! poner mensaje de, se te iba a cobrar esto, pero se te cobró esto.
        int costoFinal = (int) ((int) costo * 0.9);
        String[] text = { "el costo de la reserva es: " + costo,
                "pero gracias a un descuesto", "el precio es: " + costoFinal };
        showInfoFrameLargo(text);
        String textoFactura = "La tarjeta " + numTarjeta + ", de titular " + titularTarjeta + " de cédula ["
                + IdReservante + "] pagó la reserva exitosamente por el valor de: $" + costoFinal + ".";
        try {
            escribirFactura(textoFactura, "GooglePay.txt");
        } catch (IOException e) {
        }
    }

    public void hacerPagoGeneral(String titularTarjeta, String IdReservante, String numTarjeta, int cobroAHacer) {
        String[] text = { "el costo de los servicios es: " + cobroAHacer,
                "Se tuvo en cuenta todos los servicios no pagos",
                "del reservante, sus acompañante y cargos a la habitación" };
        showInfoFrameLargo(text);
        String textoFactura = "La tarjeta " + numTarjeta + ", de titular " + titularTarjeta + " de cédula ["
                + IdReservante + "] pagó los servicios exitosamente por el valor de: $" + cobroAHacer + ".";
        try {
            escribirFactura(textoFactura, "GooglePay.txt");
        } catch (IOException e) {
        }
        Servicios.checkOutSolido(IdReservante);
    }

}
