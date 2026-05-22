package MonopolyLiteEdition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        this.cartas = new ArrayList<>();
    }

    public Baraja(List<Carta> cartas) {
        this.cartas = (cartas != null) ? new ArrayList<>(cartas) : new ArrayList<>();
    }

    public void cargarDesdeJson(JsonObject json) {
        this.cartas = new ArrayList<>();

        if (json == null || !json.has("cartasSuerte")) {
            return;
        }

        JsonArray arrayCartas = json.getAsJsonArray("cartasSuerte");
        for (int i = 0; i < arrayCartas.size(); i++) {
            JsonObject cartaJson = arrayCartas.get(i).getAsJsonObject();

            int id = cartaJson.has("id") ? cartaJson.get("id").getAsInt() : 0;
            String descripcion = cartaJson.has("descripcion") ? cartaJson.get("descripcion").getAsString() : "";
            String tipo = cartaJson.has("tipo") ? cartaJson.get("tipo").getAsString() : "OTRO";
            int importe = cartaJson.has("importe") ? cartaJson.get("importe").getAsInt() : 0;

            cartas.add(new Carta(id, descripcion, tipo, importe));
        }

        mezclarBaraja();
    }

    public void mezclarBaraja() {
        Collections.shuffle(cartas);
    }

    public Carta obtenerCartaSiguiente() {
        if (cartas.isEmpty()) {
            return null;
        }
        Carta carta = cartas.remove(0);
        cartas.add(carta);
        return carta;
    }

    public List<Carta> getCartas() {
        return new ArrayList<>(cartas);
    }

    public int getTamaño() {
        return cartas.size();
    }

    @Override
    public String toString() {
        return "Baraja{" + "cartas=" + cartas.size() + '}';
    }
}