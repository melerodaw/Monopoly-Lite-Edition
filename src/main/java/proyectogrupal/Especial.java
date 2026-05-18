package proyectogrupal;

public class Especial extends Casilla {
    private String tipoEspecial;
    private int montoEfecto;

    public Especial() {
        super();
    }

    public Especial(int id, String nombre, String tipoEspecial, int montoEfecto) {
        super(id, nombre);
        this.tipoEspecial = tipoEspecial;
        this.montoEfecto = montoEfecto;
    }

    public String getTipoEspecial() { return tipoEspecial; }
    public int getMontoEfecto() { return montoEfecto; }

    public void setTipoEspecial(String tipoEspecial) { this.tipoEspecial = tipoEspecial; }
    public void setMontoEfecto(int montoEfecto) { this.montoEfecto = montoEfecto; }

    @Override
    public void ejecutarAccion(Jugador j) {
        // Ejemplo: si tipoEspecial es "BONO" suma, si es "MULTA" resta, si es "CARCEL" manda a carcel
        if (tipoEspecial == null) return;

        switch (tipoEspecial.toUpperCase()) {
            case "BONO":
                j.setSaldo(j.getSaldo() + montoEfecto);
                break;
            case "MULTA":
                j.setSaldo(j.getSaldo() - montoEfecto);
                break;
            case "CARCEL":
                j.setEstaEnCarcel(true);
                j.setTurnosRestantesCarcel(2); // ejemplo
                break;
            default:
                // sin efecto
                break;
        }
    }

    @Override
    public String toString() {
        return "Especial{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", tipoEspecial='" + tipoEspecial + '\'' +
                ", montoEfecto=" + montoEfecto +
                '}';
    }
}