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

public class Dado {
 
    // Atributos
    private int ultimoResultado;
 
    // Constructor por defecto
    public Dado() {
        this.ultimoResultado = 0;
    }
 
    // Constructor por parametros
    // El dado no tiene parametros variables en este juego,
    // pero se incluye por estructura de la clase
    public Dado(int ultimoResultado) {
        this.ultimoResultado = ultimoResultado;
    }
 
    // Constructor de copia
    public Dado(Dado d) {
        this.ultimoResultado = d.ultimoResultado;
    }
 
    // Getters
    public int getUltimoResultado() {
        return this.ultimoResultado;
    }
 
    // Setters
    public void setUltimoResultado(int ultimoResultado) {
        this.ultimoResultado = ultimoResultado;
    }
 
    // toString
    @Override
    public String toString() {
        return "Dado [ultimoResultado=" + ultimoResultado + "]";
    }
}
 