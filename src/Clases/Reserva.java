package Clases;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int sala;
    public String usuarioReserva;
    private double valorBoleta;
    private String asiento;

    public String pelicula;
    public Reserva(int sala, String usuarioReserva, double valorBoleta, String asiento, String pelicula){

        this.sala = sala;
        this.usuarioReserva = usuarioReserva;
        this.valorBoleta = valorBoleta;
        this.asiento = asiento;
        this.pelicula = pelicula;
    }

    public static void guardarReserva(int sala, double valorBoleta, String asiento, String usuario, String pelicula){
        Reserva nuevaReserva = new Reserva(sala, usuario, valorBoleta, asiento, pelicula);
        reservas.add(nuevaReserva);
    }

    public static void mostrarReservas(){

        for (Reserva reserva:reservas) {
            System.out.println("Asiento: " + reserva.asiento + " Usuario: " + reserva.usuarioReserva + " Sala: " + reserva.sala + " Valor boleta: " +reserva.valorBoleta+
                    "Pelicula: " + reserva.pelicula);
        }
    }
    public static List<Reserva> reservas = new ArrayList<>();
}
