package MonopolyLiteEdition;

public class Salida extends Casilla {
    private int montoEfecto;

    public Salida() {
        super();
    }

    public Salida(int id, String nombre, int montoEfecto) {
        super(id, nombre);
        this.montoEfecto = montoEfecto;
    }

    public Salida(Salida s) {
        super(s.getId(), s.getNombre());
        this.montoEfecto = s.montoEfecto;
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
        j.setSaldo(j.getSaldo() + montoEfecto);
    }

    @Override
    public String toString() {
        return "Salida{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", montoEfecto=" + montoEfecto +
                '}';
    }
}

