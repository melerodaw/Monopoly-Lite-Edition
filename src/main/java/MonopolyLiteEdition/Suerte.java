package MonopolyLiteEdition;

public class Suerte extends Casilla {

    public Suerte() {
        super();
    }

    public Suerte(int id, String nombre) {
        super(id, nombre);
    }

    public Suerte(Suerte s) {
        super(s.getId(), s.getNombre());
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        // Pendiente: robar una carta de la baraja de suerte.
    }

    @Override
    public String toString() {
        return "Suerte " + super.toString();
    }
}
