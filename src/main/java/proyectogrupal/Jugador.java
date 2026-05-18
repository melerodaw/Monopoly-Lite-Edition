package proyectogrupal;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int saldo;
    private int posicionActual;
    private boolean estaEnCarcel;
    private int turnosRestantesCarcel;
    private List<Propiedad> propiedadesCompradas;

    public Jugador() {
        this.propiedadesCompradas = new ArrayList<>();
    }

    public Jugador(String nombre, int saldo, int posicionActual, boolean estaEnCarcel,
                   int turnosRestantesCarcel, List<Propiedad> propiedadesCompradas) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.posicionActual = posicionActual;
        this.estaEnCarcel = estaEnCarcel;
        this.turnosRestantesCarcel = turnosRestantesCarcel;
        this.propiedadesCompradas = (propiedadesCompradas != null) ? propiedadesCompradas : new ArrayList<>();
    }

    public Jugador(Jugador j) {
        this.nombre = j.nombre;
        this.saldo = j.saldo;
        this.posicionActual = j.posicionActual;
        this.estaEnCarcel = j.estaEnCarcel;
        this.turnosRestantesCarcel = j.turnosRestantesCarcel;
        this.propiedadesCompradas = new ArrayList<>(j.propiedadesCompradas);
    }

    public String getNombre() { return nombre; }
    public int getSaldo() { return saldo; }
    public int getPosicionActual() { return posicionActual; }
    public boolean getEstaEnCarcel() { return estaEnCarcel; }
    public int getTurnosRestantesCarcel() { return turnosRestantesCarcel; }
    public List<Propiedad> getPropiedadesCompradas() { return propiedadesCompradas; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSaldo(int saldo) { this.saldo = saldo; }
    public void setPosicionActual(int posicionActual) { this.posicionActual = posicionActual; }
    public void setEstaEnCarcel(boolean estaEnCarcel) { this.estaEnCarcel = estaEnCarcel; }
    public void setTurnosRestantesCarcel(int turnosRestantesCarcel) { this.turnosRestantesCarcel = turnosRestantesCarcel; }
    public void setPropiedadesCompradas(List<Propiedad> propiedadesCompradas) {
        this.propiedadesCompradas = (propiedadesCompradas != null) ? propiedadesCompradas : new ArrayList<>();
    }

    public void agregarPropiedad(Propiedad p) {
        if (this.propiedadesCompradas == null) this.propiedadesCompradas = new ArrayList<>();
        this.propiedadesCompradas.add(p);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                ", posicionActual=" + posicionActual +
                ", estaEnCarcel=" + estaEnCarcel +
                ", turnosRestantesCarcel=" + turnosRestantesCarcel +
                ", propiedadesCompradas=" + (propiedadesCompradas != null ? propiedadesCompradas.size() : 0) +
                '}';
    }
}