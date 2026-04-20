/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectogrupal;

/**
 *
 * @author EAG
 */
public class Propiedad {
 
    // Atributos
    private String nombre;
    private int precio;
    private int alquiler;
    private String grupo;
    private Jugador duenio;
 
    // Constructor por defecto
    public Propiedad() {
        this.nombre = "Sin nombre";
        this.precio = 0;
        this.alquiler = 0;
        this.grupo = "Sin grupo";
        this.duenio = null;
    }
 
    // Constructor por parametros
    public Propiedad(String nombre, int precio, int alquiler, String grupo) {
        this.nombre = nombre;
        this.precio = precio;
        this.alquiler = alquiler;
        this.grupo = grupo;
        this.duenio = null;
    }
 
    // Constructor de copia
    public Propiedad(Propiedad p) {
        this.nombre = p.nombre;
        this.precio = p.precio;
        this.alquiler = p.alquiler;
        this.grupo = p.grupo;
        this.duenio = p.duenio;
    }
 
    // Getters
    public String getNombre() {
        return this.nombre;
    }
 
    public int getPrecio() {
        return this.precio;
    }
 
    public int getAlquiler() {
        return this.alquiler;
    }
 
    public String getGrupo() {
        return this.grupo;
    }
 
    public Jugador getDuenio() {
        return this.duenio;
    }
 
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public void setPrecio(int precio) {
        this.precio = precio;
    }
 
    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }
 
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
 
    public void setDuenio(Jugador duenio) {
        this.duenio = duenio;
    }
 
    // toString
    @Override
    public String toString() {
        return "Propiedad [nombre=" + nombre + ", precio=" + precio + ", alquiler=" + alquiler + ", grupo=" + grupo + ", duenio=" + (duenio != null ? duenio.getNombre() : "Ninguno") + "]";
    }
}