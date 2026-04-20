package proyectogrupal;

public class Banca {
    private boolean fondoInfinito;

    public Banca() {}

    public Banca(boolean fondoInfinito) {
        this.fondoInfinito = fondoInfinito;
    }

    public Banca(Banca b) {
        this.fondoInfinito = b.fondoInfinito;
    }

    public boolean getFondoInfinito() { return fondoInfinito; }
    public void setFondoInfinito(boolean fondoInfinito) { this.fondoInfinito = fondoInfinito; }

    public void cobrarImpuesto(Jugador j, int monto) {
        if (j == null) return;
        j.setSaldo(j.getSaldo() - monto);
    }

    public void pagarBonoSalida(Jugador j, int monto) {
        if (j == null) return;
        j.setSaldo(j.getSaldo() + monto);
    }

    @Override
    public String toString() {
        return "Banca{fondoInfinito=" + fondoInfinito + "}";
    }
}