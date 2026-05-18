package MonopolyLiteEdition;

public class Impuesto extends Casilla {
    private int montoEfecto;

    public Impuesto() {
        super();
    }

    public Impuesto(int id, String nombre, int montoEfecto) {
        super(id, nombre);
        this.montoEfecto = montoEfecto;
    }

    public Impuesto(Impuesto i) {
        super(i.getId(), i.getNombre());
        this.montoEfecto = i.montoEfecto;
    }

    public int getMontoEfecto() {
        return montoEfecto;
    }

    public void setMontoEfecto(int montoEfecto) {
        this.montoEfecto = montoEfecto;
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        if (j == null) {
            return;
        }
        j.setSaldo(j.getSaldo() - montoEfecto);
    }

    @Override
    public String toString() {
        return "Impuesto{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", montoEfecto=" + montoEfecto +
                '}';
    }
}

