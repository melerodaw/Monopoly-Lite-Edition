package proyectogrupal;

public class Accion{
    private int id;
    private String descripcionCarta;

    public Accion() {
        this.id = 0;
    }

    public Accion(int id, String descripcionCarta) {
        this.id = id;
        this.descripcionCarta = descripcionCarta;
    }

    public Accion(String descripcionCarta) {
        this(0, descripcionCarta);
    }

    public Accion(Accion a) {
        this.id = a.id;
        this.descripcionCarta = a.descripcionCarta;
    }

    public int getId() { return id; }

    public String getDescripcionCarta() { return descripcionCarta; }
    public void setId(int id) { this.id = id; }
    public void setDescripcionCarta(String descripcionCarta) { this.descripcionCarta = descripcionCarta; }

    @Override
    public String toString() {
        return "Accion{id=" + id + ", descripcionCarta='" + descripcionCarta + "'}";
    }
}