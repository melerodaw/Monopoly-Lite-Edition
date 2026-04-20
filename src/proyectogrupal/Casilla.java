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
public class Casilla {

    // Atributos
    private int id;
    private String nombre;

    // Constructor por defecto
    public Casilla() {
        this.id = 0;
        this.nombre = "Sin nombre";
    }

    // Constructor por parametros
    public Casilla(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor de copia
    public Casilla(Casilla c) {
        this.id = c.id;
        this.nombre = c.nombre;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Metodo segun UML: ejecutarAccion(j: Jugador)
    // En la clase base normalmente no hace nada, y las hijas (Especial/Propiedad)
    // lo pueden sobreescribir.
    public void ejecutarAccion(Jugador j) {
        // Sin accion por defecto
    }

    // toString
    @Override
    public String toString() {
        return "Casilla [id=" + id + ", nombre=" + nombre + "]";
    }
}