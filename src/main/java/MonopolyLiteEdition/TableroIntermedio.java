package MonopolyLiteEdition;

import java.util.ArrayList;

public class TableroIntermedio {
    private ArrayList<Salida> salida;
    private ArrayList<Propiedad> propiedad;
    private ArrayList<Suerte> suerte;
    private ArrayList<Transporte> transporte;
    private ArrayList<Carcel> carcel;
    private ArrayList<IrACarcel> irACarcel;
    private ArrayList<Impuesto> impuesto;

    public TableroIntermedio() {
        this.salida = new ArrayList<>();
        this.propiedad = new ArrayList<>();
        this.suerte = new ArrayList<>();
        this.transporte = new ArrayList<>();
        this.carcel = new ArrayList<>();
        this.irACarcel = new ArrayList<>();
        this.impuesto = new ArrayList<>();
    }

    public TableroIntermedio(ArrayList<Salida> salida, ArrayList<Propiedad> propiedad, ArrayList<Suerte> suerte,
                             ArrayList<Transporte> transporte, ArrayList<Carcel> carcel,
                             ArrayList<IrACarcel> irACarcel, ArrayList<Impuesto> impuesto) {
        this.salida = salida;
        this.propiedad = propiedad;
        this.suerte = suerte;
        this.transporte = transporte;
        this.carcel = carcel;
        this.irACarcel = irACarcel;
        this.impuesto = impuesto;
    }

    public TableroIntermedio(TableroIntermedio t) {
        this.salida = t.salida != null ? new ArrayList<>(t.salida) : new ArrayList<>();
        this.propiedad = t.propiedad != null ? new ArrayList<>(t.propiedad) : new ArrayList<>();
        this.suerte = t.suerte != null ? new ArrayList<>(t.suerte) : new ArrayList<>();
        this.transporte = t.transporte != null ? new ArrayList<>(t.transporte) : new ArrayList<>();
        this.carcel = t.carcel != null ? new ArrayList<>(t.carcel) : new ArrayList<>();
        this.irACarcel = t.irACarcel != null ? new ArrayList<>(t.irACarcel) : new ArrayList<>();
        this.impuesto = t.impuesto != null ? new ArrayList<>(t.impuesto) : new ArrayList<>();
    }

    public ArrayList<Salida> getSalida() {
        return salida;
    }

    public void setSalida(ArrayList<Salida> salida) {
        this.salida = salida;
    }

    public ArrayList<Propiedad> getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(ArrayList<Propiedad> propiedad) {
        this.propiedad = propiedad;
    }

    public ArrayList<Suerte> getSuerte() {
        return suerte;
    }

    public void setSuerte(ArrayList<Suerte> suerte) {
        this.suerte = suerte;
    }

    public ArrayList<Transporte> getTransporte() {
        return transporte;
    }

    public void setTransporte(ArrayList<Transporte> transporte) {
        this.transporte = transporte;
    }

    public ArrayList<Carcel> getCarcel() {
        return carcel;
    }

    public void setCarcel(ArrayList<Carcel> carcel) {
        this.carcel = carcel;
    }

    public ArrayList<IrACarcel> getIrACarcel() {
        return irACarcel;
    }

    public void setIrACarcel(ArrayList<IrACarcel> irACarcel) {
        this.irACarcel = irACarcel;
    }

    public ArrayList<Impuesto> getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(ArrayList<Impuesto> impuesto) {
        this.impuesto = impuesto;
    }

    @Override
    public String toString() {
        return "TableroIntermedio{" +
                "salida=" + (salida != null ? salida.size() : 0) +
                ", propiedad=" + (propiedad != null ? propiedad.size() : 0) +
                ", suerte=" + (suerte != null ? suerte.size() : 0) +
                ", transporte=" + (transporte != null ? transporte.size() : 0) +
                ", carcel=" + (carcel != null ? carcel.size() : 0) +
                ", irACarcel=" + (irACarcel != null ? irACarcel.size() : 0) +
                ", impuesto=" + (impuesto != null ? impuesto.size() : 0) +
                '}';
    }
}

