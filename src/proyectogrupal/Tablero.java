package proyectogrupal;

public class Tablero {
    private Casilla[] casillas; // [20] en el UML

    public Tablero() {
        this.casillas = new Casilla[20];
    }

    public Tablero(Casilla[] casillas) {
        this.casillas = (casillas != null) ? casillas : new Casilla[20];
    }

    public Tablero(Tablero t) {
        this.casillas = t.casillas != null ? t.casillas.clone() : new Casilla[20];
    }

    public Casilla[] getCasillas() { return casillas; }

    public void setCasillas(Casilla[] casillas) {
        this.casillas = (casillas != null) ? casillas : new Casilla[20];
    }

    public Casilla obtenerCasillas(int index) {
        if (casillas == null) return null;
        if (index < 0 || index >= casillas.length) return null;
        return casillas[index];
    }

    public int calcularNuevaPosicion(int posicionActual, int avance) {
        int size = (casillas != null) ? casillas.length : 20;
        return Math.floorMod(posicionActual + avance, size);
    }

    @Override
    public String toString() {
        return "Tablero{casillas=" + (casillas != null ? casillas.length : 0) + "}";
    }
}