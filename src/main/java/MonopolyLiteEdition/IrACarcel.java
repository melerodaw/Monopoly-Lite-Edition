package MonopolyLiteEdition;

public class IrACarcel extends Casilla {
    private int destino;

    public IrACarcel() {
        super();
        this.destino = 5;
    }

    public IrACarcel(int id, String nombre, int destino) {
        super(id, nombre);
        this.destino = destino;
    }

    public IrACarcel(IrACarcel i) {
        super(i.getId(), i.getNombre());
        this.destino = i.destino;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        if (j == null) {
            return;
        }
        j.setPosicionActual(destino);
        j.setEstaEnCarcel(true);
        j.setTurnosRestantesCarcel(2);
    }

    @Override
    public String toString() {
        return "IrACarcel{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", destino=" + destino +
                '}';
    }
}

