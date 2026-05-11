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
    private int id;
    private String descripcion;
    private String tipo;
    private int importe;
 
    // Constructor por defecto
    public Carta() {
        this.id = 0;
        this.descripcion = "Sin descripcion";
        this.tipo = "SIN_TIPO";
        this.importe = 0;
    }
 
    // Constructor por parametros
    public Carta(int id, String descripcion, String tipo, int importe) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.importe = importe;
    }

    // Constructor compatible con el modelo anterior
    public Carta(String descripcion, int importe) {
        this(0, descripcion, "SUERTE", importe);
    }
 
    // Constructor de copia
    public Carta(Carta c) {
        this.id = c.id;
        this.descripcion = c.descripcion;
        this.tipo = c.tipo;
        this.importe = c.importe;
    }
 
    // Getters
    public int getId() {
        return this.id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getTipo() {
        return this.tipo;
    }
 
    public int getImporte() {
        return this.importe;
    }
 
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 
    public void setImporte(int importe) {
        this.importe = importe;
    }
 
    // toString
    @Override
    public String toString() {
        return "Carta [id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + ", importe=" + importe + "]";
    }
}
