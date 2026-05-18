package MonopolyLiteEdition;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Tablero {
    private static final int TOTAL_CASILLAS = 20;

    private Casilla[] casillas;

    public Tablero() {
        this.casillas = new Casilla[TOTAL_CASILLAS];
    }

    public Tablero(Casilla[] casillas) {
        this.casillas = (casillas != null) ? casillas : new Casilla[TOTAL_CASILLAS];
    }

    public Tablero(JsonObject json) {
        this();
        cargarDesdeJson(json);
    }

    public Tablero(Tablero t) {
        this.casillas = t.casillas != null ? t.casillas.clone() : new Casilla[TOTAL_CASILLAS];
    }

    public static Tablero fromJson(JsonObject json) {
        return new Tablero(json);
    }

    public Casilla[] getCasillas() { return casillas; }

    public void setCasillas(Casilla[] casillas) {
        this.casillas = (casillas != null) ? casillas : new Casilla[TOTAL_CASILLAS];
    }

    public Casilla obtenerCasillas(int index) {
        if (casillas == null) return null;
        if (index < 0 || index >= casillas.length) return null;
        return casillas[index];
    }

    public void cargarDesdeJson(JsonObject json) {
        this.casillas = new Casilla[TOTAL_CASILLAS];

        if (json == null) {
            return;
        }

        JsonObject tableroJson = json.has("tablero") && json.get("tablero").isJsonObject()
                ? json.getAsJsonObject("tablero")
                : json;

        JsonArray arrayCasillas = tableroJson.has("casillas") && tableroJson.get("casillas").isJsonArray()
                ? tableroJson.getAsJsonArray("casillas")
                : null;

        if (arrayCasillas == null) {
            return;
        }

        for (int i = 0; i < casillas.length && i < arrayCasillas.size(); i++) {
            JsonElement elemento = arrayCasillas.get(i);
            if (elemento != null && elemento.isJsonObject()) {
                casillas[i] = Casilla.fromJson(elemento.getAsJsonObject());
            }
        }
    }

    public int calcularNuevaPosicion(int posicionActual, int avance) {
        int size = (casillas != null) ? casillas.length : TOTAL_CASILLAS;
        return Math.floorMod(posicionActual + avance, size);
    }

    @Override
    public String toString() {
        return "Tablero{casillas=" + (casillas != null ? casillas.length : 0) + "}";
    }
}
