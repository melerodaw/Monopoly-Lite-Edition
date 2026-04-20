public class Accion {
    private String descripcionCarta;

    public Accion() {}

    public Accion(String descripcionCarta) {
        this.descripcionCarta = descripcionCarta;
    }

    public Accion(Accion a) {
        this.descripcionCarta = a.descripcionCarta;
    }

    public String getDescripcionCarta() { return descripcionCarta; }
    public void setDescripcionCarta(String descripcionCarta) { this.descripcionCarta = descripcionCarta; }

    @Override
    public String toString() {
        return "Accion{descripcionCarta='" + descripcionCarta + "'}";
    }
}