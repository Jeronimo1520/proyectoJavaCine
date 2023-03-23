package Clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    public static List<Usuario> usuarios = new ArrayList<>();
    public String nombre;

    public int edad;
    public final Genero genero;

    public Usuario(String nombre, int edad, Genero genero){
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }


    public enum Genero{ //representa conjuntos de constantes con un nombre.
        FEMENINO, MASCULINO, NOBINARIO, OTRO;
    }
}