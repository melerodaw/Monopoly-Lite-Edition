public class Propiedad extends Casilla {
    private int precioCompra;
    private int alquilerBase;
    private String grupo;
    private Jugador propietario;
    private boolean esDeGrupoCompleto;

    public Propiedad() {
        super();
    }

    public Propiedad(int id, String nombre, int precioCompra, int alquilerBase, String grupo,
                     Jugador propietario, boolean esDeGrupoCompleto) {
        super(id, nombre);
        this.precioCompra = precioCompra;
        this.alquilerBase = alquilerBase;
        this.grupo = grupo;
        this.propietario = propietario;
        this.esDeGrupoCompleto = esDeGrupoCompleto;
    }

    public Propiedad(Propiedad p) {
        super(p.getId(), p.getNombre());
        this.precioCompra = p.precioCompra;
        this.alquilerBase = p.alquilerBase;
        this.grupo = p.grupo;
        this.propietario = p.propietario;
        this.esDeGrupoCompleto = p.esDeGrupoCompleto;
    }

    public int getPrecioCompra() { return precioCompra; }
    public int getAlquilerBase() { return alquilerBase; }
    public String getGrupo() { return grupo; }
    public Jugador getPropietario() { return propietario; }
    public boolean isEsDeGrupoCompleto() { return esDeGrupoCompleto; }

    public void setPrecioCompra(int precioCompra) { this.precioCompra = precioCompra; }
    public void setAlquilerBase(int alquilerBase) { this.alquilerBase = alquilerBase; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    public void setPropietario(Jugador propietario) { this.propietario = propietario; }
    public void setEsDeGrupoCompleto(boolean esDeGrupoCompleto) { this.esDeGrupoCompleto = esDeGrupoCompleto; }

    @Override
    public void ejecutarAccion(Jugador j) {
        // Lógica mínima típica:
        // - Si no tiene propietario: el jugador puede comprar si tiene saldo suficiente.
        // - Si tiene propietario y no es el mismo: paga alquiler.
        if (propietario == null) {
            if (j.getSaldo() >= precioCompra) {
                j.setSaldo(j.getSaldo() - precioCompra);
                this.propietario = j;
                j.agregarPropiedad(this);
            }
        } else if (propietario != j) {
            int alquiler = alquilerBase;
            if (esDeGrupoCompleto) {
                alquiler *= 2; // ejemplo simple
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
                ", grupo='" + grupo + '\'' +
                ", propietario=" + (propietario != null ? propietario.getNombre() : "null") +
                ", esDeGrupoCompleto=" + esDeGrupoCompleto +
                '}';
    }
}