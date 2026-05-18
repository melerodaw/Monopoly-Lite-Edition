package MonopolyLiteEdition;

public class Transporte extends Casilla {
    private int precioCompra;
    private int alquilerBase;
    private Jugador propietario;

    public Transporte() {
        super();
    }

    public Transporte(int id, String nombre, int precioCompra, int alquilerBase, Jugador propietario) {
        super(id, nombre);
        this.precioCompra = precioCompra;
        this.alquilerBase = alquilerBase;
        this.propietario = propietario;
    }

    public Transporte(Transporte t) {
        super(t.getId(), t.getNombre());
        this.precioCompra = t.precioCompra;
        this.alquilerBase = t.alquilerBase;
        this.propietario = t.propietario;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public int getAlquilerBase() {
        return alquilerBase;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setAlquilerBase(int alquilerBase) {
        this.alquilerBase = alquilerBase;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        if (j == null) {
            return;
        }

        if (propietario == null) {
            if (j.getSaldo() >= precioCompra) {
                j.setSaldo(j.getSaldo() - precioCompra);
                this.propietario = j;
                j.agregarPropiedad(this);
            }
        } else if (propietario != j) {
            j.setSaldo(j.getSaldo() - alquilerBase);
            propietario.setSaldo(propietario.getSaldo() + alquilerBase);
        }
    }

    @Override
    public String toString() {
        return "Transporte{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", precioCompra=" + precioCompra +
                ", alquilerBase=" + alquilerBase +
                ", propietario=" + (propietario != null ? propietario.getNombre() : "null") +
                '}';
    }
}

