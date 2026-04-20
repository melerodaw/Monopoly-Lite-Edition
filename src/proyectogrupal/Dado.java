package proyectogrupal;
import java.util.Random;

public class Dado {
    private final Random random;

    public Dado() {
        this.random = new Random();
    }

    public Dado(Dado d) {
        this.random = new Random();
    }

    public int generarAleatorio() {
        // Dado clásico 1..6
        return random.nextInt(6) + 1;
    }

    @Override
    public String toString() {
        return "Dado{}";
    }
}