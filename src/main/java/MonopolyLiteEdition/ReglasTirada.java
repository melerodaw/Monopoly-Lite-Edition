
package MonopolyLiteEdition;

import com.google.gson.JsonObject;

public class ReglasTirada {
    private int dineroInicial;
    private int premioSalida;
    private int multaImpuesto;
    private int turnosCarcel;
    private int tamanoTablero;
    private int minimoCartasSuerte;

    public ReglasTirada() {
        this.dineroInicial = 1500;
        this.premioSalida = 200;
        this.multaImpuesto = 100;
        this.turnosCarcel = 2;
        this.tamanoTablero = 20;
        this.minimoCartasSuerte = 10;
    }

    public void cargarDesdeJson(JsonObject json) {
        if (json == null || !json.has("reglas")) {
            return;
        }

        JsonObject reglas = json.getAsJsonObject("reglas");

        this.dineroInicial = reglas.has("dineroInicial") ? reglas.get("dineroInicial").getAsInt() : 1500;
        this.premioSalida = reglas.has("premioSalida") ? reglas.get("premioSalida").getAsInt() : 200;
        this.multaImpuesto = reglas.has("multaImpuesto") ? reglas.get("multaImpuesto").getAsInt() : 100;
        this.turnosCarcel = reglas.has("turnosCarcel") ? reglas.get("turnosCarcel").getAsInt() : 2;
        this.tamanoTablero = reglas.has("tamanoTablero") ? reglas.get("tamanoTablero").getAsInt() : 20;
        this.minimoCartasSuerte = reglas.has("minimoCartasSuerte") ? reglas.get("minimoCartasSuerte").getAsInt() : 10;
    }

    // Getters
    public int getDineroInicial() { return dineroInicial; }
    public int getPremioSalida() { return premioSalida; }
    public int getMultaImpuesto() { return multaImpuesto; }
    public int getTurnosCarcel() { return turnosCarcel; }
    public int getTamanoTablero() { return tamanoTablero; }
    public int getMinimoCartasSuerte() { return minimoCartasSuerte; }

    @Override
    public String toString() {
        return "ReglasTirada{" +
                "dineroInicial=" + dineroInicial +
                ", premioSalida=" + premioSalida +
                ", multaImpuesto=" + multaImpuesto +
                ", turnosCarcel=" + turnosCarcel +
                ", tamanoTablero=" + tamanoTablero +
                ", minimoCartasSuerte=" + minimoCartasSuerte +
                '}';
    }
}
