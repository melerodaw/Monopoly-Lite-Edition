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
public class Jugador {
 
    // Atributos
    private String nombre;
    private int saldo;
    private int posicion;
    private boolean enCarcel;
 
    // Constructor por defecto
    public Jugador() {
        this.nombre = "Jugador";
        this.saldo = 1500;
        this.posicion = 0;
        this.enCarcel = false;
    }
 
    // Constructor por parametros
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.saldo = 1500;
        this.posicion = 0;
        this.enCarcel = false;
    }
 
    // Constructor de copia
    public Jugador(Jugador j) {
        this.nombre = j.nombre;
        this.saldo = j.saldo;
        this.posicion = j.posicion;
        this.enCarcel = j.enCarcel;
    }
 
    // Getters
    public String getNombre() {
        return this.nombre;
    }
 
    public int getSaldo() {
        return this.saldo;
    }
 
    public int getPosicion() {
        return this.posicion;
    }
 
    public boolean isEnCarcel() {
        return this.enCarcel;
    }
 
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
 
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
 
    public void setEnCarcel(boolean enCarcel) {
        this.enCarcel = enCarcel;
    }
 
    // toString
    @Override
    public String toString() {
        return "Jugador [nombre=" + nombre + ", saldo=" + saldo + ", posicion=" + posicion + ", enCarcel=" + enCarcel + "]";
    }
}
 