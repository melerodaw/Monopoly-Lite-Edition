package MonopolyLiteEdition;

public class Suerte extends Casilla {
    private Baraja baraja;

    public Suerte() {
        super();
        this.baraja = null;
    }

    public Suerte(int id, String nombre) {
        super(id, nombre);
        this.baraja = null;
    }

    public Suerte(Suerte s) {
        super(s.getId(), s.getNombre());
        this.baraja = s.baraja;
    }

    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    /**
     * Saca la siguiente carta de la baraja, aplica su efecto al jugador
     * y devuelve una descripción del efecto (para mostrar en la UI).
     */
    public String sacarYAplicarCarta(Jugador j) {
        if (j == null || baraja == null) return "No hay carta disponible";

        Carta carta = baraja.obtenerCartaSiguiente();
        if (carta == null) return "No hay carta disponible";

        String descripcion = carta.getDescripcion();

        switch (carta.getTipo().toUpperCase()) {
            case "BONO":
                j.setSaldo(j.getSaldo() + carta.getImporte());
                descripcion += " → +$" + carta.getImporte();
                break;
            case "MULTA":
                j.setSaldo(j.getSaldo() - carta.getImporte());
                descripcion += " → -$" + carta.getImporte();
                break;
            case "AVANZA":
                j.setPosicionActual(0);
                j.setSaldo(j.getSaldo() + carta.getImporte());
                descripcion += " → Avanzas a SALIDA y ganas $" + carta.getImporte();
                break;
            case "RETROCEDE":
                int nuevaPos = Math.max(0, j.getPosicionActual() - 3);
                j.setPosicionActual(nuevaPos);
                descripcion += " → Retrocedes 3 casillas";
                break;
            case "CARCEL":
                j.setPosicionActual(5);
                j.setEstaEnCarcel(true);
                j.setTurnosRestantesCarcel(2);
                descripcion += " → ¡A la cárcel!";
                break;
            case "OTRO":
            default:
                descripcion += " → Efecto especial";
                break;
        }

        return descripcion;
    }

    @Override
    public void ejecutarAccion(Jugador j) {
        // Delegar a la versión que devuelve descripción (útil para UI)
        sacarYAplicarCarta(j);
    }

    @Override
    public String toString() {
        return "Suerte{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                '}';
    }
}