import Clases.Pelicula;
import Clases.Sala;
import Clases.Usuario;
import Clases.Reserva;

import static Clases.Reserva.reservas;
import static Clases.Usuario.usuarios;
import java.util.Scanner;
public class Main {
    final static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Pelicula pelicula1 = new Pelicula("El gato con botas", 10);
        Pelicula pelicula2= new Pelicula("Pinochio", 10);
        Sala sala1 = new Sala(4,4,1, pelicula1);
        Sala sala2 = new Sala(5, 4,2, pelicula2);
        sala1.llenarSala();
        sala2.llenarSala();

        String menu = "Bienvenido, eliga una opcion\n" +
                        "1.Reservar silla\n" +
                        "2.Mostrar estadisticas\n" +
                        "3.Mostrar reservas\n"+
                        "4.Salir";
        int opcion;
        do {
            System.out.println(menu);
            opcion = scan.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Ingrese su nombre");
                    String nombre = scan.next();
                    System.out.println("Ingrese su edad");
                    int edad = scan.nextInt();
                    System.out.println("Ingrese su genero\n" +
                            "1.M: masculino\n" +
                            "2.F:Femenino\n" +
                            "3.NB:No binario\n" +
                            "4.Otro");
                    int genero = scan.nextInt();
                    Usuario nuevoUsuario = crearUsuario(nombre,edad,genero);
                    for (Usuario usuario:usuarios) {
                        System.out.println(usuario.nombre);
                    }
                    System.out.println("Elija la pelicula que desea ver: \n 1."+ pelicula1.nombre + "\n 2."+pelicula2.nombre);
                    int peliOpcion = scan.nextInt();
                    if(peliOpcion == 1){
                        sala1.imprimirSala();
                        String asiento = reservaAsiento(sala1);
                        Reserva.guardarReserva(sala1.numeroSala,pelicula1.precioEntrada,asiento,nuevoUsuario.nombre, pelicula1.nombre);
                        System.out.println("Reserva realizada con éxito \n");
                    }else{
                        sala2.imprimirSala();
                        String asiento = reservaAsiento(sala2);
                        Reserva.guardarReserva(sala2.numeroSala,pelicula2.precioEntrada,asiento,nuevoUsuario.nombre, pelicula2.nombre);
                        System.out.println("Reserva realizada con éxito \n");
                    }
                    break;
                case 2:
                    porcentajeMujeres();
                    porcentajeHombres();
                    peliculaMasReservada();
                    usuarioMasViejo();
                    usuarioMasJoven();
                    System.out.println("\n");
                    break;
                case 3:
                    Reserva.mostrarReservas();
                    break;
            }
        }while (opcion != 4);
    }
    private static Usuario crearUsuario(String nombre, int edad, int genero) {
        Usuario.Genero generoUsuario = null;
        switch (genero){
            case 1:
                generoUsuario = Usuario.Genero.MASCULINO;
                break;
            case 2:
                generoUsuario = Usuario.Genero.FEMENINO;
                break;
            case 3:
                generoUsuario = Usuario.Genero.NOBINARIO;
                break;
            case 4:
                generoUsuario = Usuario.Genero.OTRO;
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
        Usuario nuevoUsuario = new Usuario(nombre,edad,generoUsuario);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    public static String reservaAsiento(Sala sala){
        boolean flag;
        int fila;
        int columna;
        do{
            System.out.println("Ingrese un numero de fila");
            fila = scan.nextInt();
            System.out.println("Ingrese un numero de columna");
            columna = scan.nextInt();
            flag = sala.verificarAsiento(fila,columna);
        }while (!flag);
        return Integer.toString(fila) + "-"+ Integer.toString(columna);
    }

    public static void porcentajeMujeres(){ //Cuantas mujeres han reservado
        float totalUsuarios= 0;
        float totalMujeres = 0;
        for (Usuario usuario: usuarios) {
            if(usuario.genero.equals(Usuario.Genero.FEMENINO)){
                totalMujeres++;
            }
            totalUsuarios++;
        }
        float total = (totalMujeres/totalUsuarios)*100;
        System.out.println("El porcentaje de mujeres que han reserado son:"+ total);
    }
    public static void porcentajeHombres(){ //Cuantas Hombres han reservado
        float totalUsuarios= 0;
        float totalHombres= 0;
        for (Usuario usuario: usuarios) {
            if(usuario.genero.equals(Usuario.Genero.MASCULINO)){
                totalHombres++;
            }
            totalUsuarios++;
        }
        float total = (totalHombres/totalUsuarios)*100;
        System.out.println("El porcentaje de hombres que han reserado son:"+(totalHombres/totalUsuarios)*100);
    }

    public static void peliculaMasReservada(){ //Cuantas Hombres han reservado
        int contPel1 = 0;
        int contPel2 = 0;
        for (Reserva reserva:reservas) {
            if(reserva.pelicula == "El gato con botas"){
                contPel1++;
            } else if (reserva.pelicula == "Pinochio"){
                contPel2++;
            }else{
                System.out.println("Ninguna pelicula ha sido reservada");
            }
        }
        if(contPel1 > contPel2){
            System.out.println("El gato con botas ha sido mas reservada");
        }else if (contPel1 == contPel2){
            System.out.println("Ambas peliculas cuantas con igual numero de reservas");
        }else{
            System.out.println("Pinochio ha sido mas reservada");
        }
    }
    public static void usuarioMasViejo(){
        String nombreUsuario = "";
        int edadMax = 0;
        for (Usuario usuario:usuarios){
            if(usuario.edad > edadMax){
                edadMax = usuario.edad;
                nombreUsuario = usuario.nombre;
            }
        } System.out.println("El usuario mas viejo es: " + nombreUsuario);
    }
    public static void usuarioMasJoven(){
        String nombreUsuario = "";
        int edadMin = 100;
        for (Usuario usuario:usuarios){
            if(usuario.edad > edadMin){
                edadMin = usuario.edad;
                nombreUsuario = usuario.nombre;
            }
        }System.out.println("El usuario mas joven es: " + nombreUsuario);
    }
}