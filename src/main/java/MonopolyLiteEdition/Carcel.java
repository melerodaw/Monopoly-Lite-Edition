package MonopolyLiteEdition;

public class Carcel extends Casilla {

    public Carcel() {
        super();
    }

    public Carcel(int id, String nombre) {
        super(id, nombre);
    }

    public Carcel(Carcel c) {
        super(c.getId(), c.getNombre());
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        // Sin efecto: solo visitas la cárcel.
    }

    @Override
    public String toString() {
        return "Carcel{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                '}';
    }
}
