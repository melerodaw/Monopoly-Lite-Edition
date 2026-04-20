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
public class Carta {
 
    // Atributos
    private String descripcion;
    private int importe;
 
    // Constructor por defecto
    public Carta() {
        this.descripcion = "Sin descripcion";
        this.importe = 0;
    }
 
    // Constructor por parametros
    public Carta(String descripcion, int importe) {
        this.descripcion = descripcion;
        this.importe = importe;
    }
 
    // Constructor de copia
    public Carta(Carta c) {
        this.descripcion = c.descripcion;
        this.importe = c.importe;
    }
 
    // Getters
    public String getDescripcion() {
        return this.descripcion;
    }
 
    public int getImporte() {
        return this.importe;
    }
 
    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    public void setImporte(int importe) {
        this.importe = importe;
    }
 
    // toString
    @Override
    public String toString() {
        return "Carta [descripcion=" + descripcion + ", importe=" + importe + "]";
    }
}
