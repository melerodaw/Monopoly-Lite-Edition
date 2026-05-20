package MonopolyLiteEdition;

public class Propiedad extends Casilla {
    private int precioCompra;
    private int alquilerBase;
    private Jugador propietario;
    private boolean esDeGrupoCompleto;

    public Propiedad() {
        super();
    }

    public Propiedad(int id, String nombre, int precioCompra, int alquilerBase,
                     Jugador propietario, boolean esDeGrupoCompleto) {
        super(id, nombre);
        this.precioCompra = precioCompra;
        this.alquilerBase = alquilerBase;
        this.propietario = propietario;
        this.esDeGrupoCompleto = esDeGrupoCompleto;
    }

    public Propiedad(Propiedad p) {
        super(p.getId(), p.getNombre());
        this.precioCompra = p.precioCompra;
        this.alquilerBase = p.alquilerBase;
        this.propietario = p.propietario;
        this.esDeGrupoCompleto = p.esDeGrupoCompleto;
    }

    public int getPrecioCompra() { return precioCompra; }
    public int getAlquilerBase() { return alquilerBase; }
    public Jugador getPropietario() { return propietario; }
    public boolean isEsDeGrupoCompleto() { return esDeGrupoCompleto; }

    public void setPrecioCompra(int precioCompra) { this.precioCompra = precioCompra; }
    public void setAlquilerBase(int alquilerBase) { this.alquilerBase = alquilerBase; }
    public void setPropietario(Jugador propietario) { this.propietario = propietario; }
    public void setEsDeGrupoCompleto(boolean esDeGrupoCompleto) { this.esDeGrupoCompleto = esDeGrupoCompleto; }

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
            int alquiler = alquilerBase;
            if (esDeGrupoCompleto) {
                alquiler *= 2;
            }
            j.setSaldo(j.getSaldo() - alquiler);
            propietario.setSaldo(propietario.getSaldo() + alquiler);
        }
    }

    @Override
    public String toString() {
        return "Propiedad{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", precioCompra=" + precioCompra +
                ", alquilerBase=" + alquilerBase +
                ", grupo='" + getNombre().charAt(0) + '\'' +
                ", propietario=" + (propietario != null ? propietario.getNombre() : "null") +
                ", esDeGrupoCompleto=" + esDeGrupoCompleto +
                '}';
    }
}