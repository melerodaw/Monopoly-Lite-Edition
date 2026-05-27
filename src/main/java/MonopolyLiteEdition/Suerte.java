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

    @Override
    public void ejecutarAccion(Jugador j) {
        if (j == null || baraja == null) {
            return;
        }

        Carta carta = baraja.obtenerCartaSiguiente();
        if (carta == null) {
            return;
        }

        System.out.println("  ➜ " + j.getNombre() + " sacó carta: " + carta.getDescripcion());

        switch (carta.getTipo().toUpperCase()) {
            case "BONO":
                j.setSaldo(j.getSaldo() + carta.getImporte());
                System.out.println("    ✓ Ganas $" + carta.getImporte());
                break;
            case "MULTA":
                j.setSaldo(j.getSaldo() - carta.getImporte());
                System.out.println("    ✗ Pierdes $" + carta.getImporte());
                break;
            case "AVANZA":
                j.setPosicionActual(0);
                j.setSaldo(j.getSaldo() + carta.getImporte());
                System.out.println("    ↻ Avanzo a SALIDA y gano $" + carta.getImporte());
                break;
            case "RETROCEDE":
                int nuevaPos = Math.max(0, j.getPosicionActual() - 3);
                j.setPosicionActual(nuevaPos);
                System.out.println("    ↺ Retrocedí 3 casillas");
                break;
            case "CARCEL":
                j.setPosicionActual(5);
                j.setEstaEnCarcel(true);
                j.setTurnosRestantesCarcel(2);
                System.out.println("    🔒 ¡A CARCEL!");
                break;
            case "OTRO":
                System.out.println("    ℹ Efecto especial");
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Suerte{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                '}';
    }
}