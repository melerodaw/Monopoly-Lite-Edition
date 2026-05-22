package MonopolyLiteEdition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TableroManager {
    private Tablero tablero;

    public TableroManager() {
        this.tablero = new Tablero();
    }

    public void cargarTableroDesdeJson(JsonObject json) {
        Casilla[] casillas = new Casilla[20];

        if (json == null) {
            return;
        }

        // Cargar SALIDA
        if (json.has("salida") && json.get("salida").isJsonArray()) {
            JsonArray salidas = json.getAsJsonArray("salida");
            if (salidas.size() > 0) {
                JsonObject salidaJson = salidas.get(0).getAsJsonObject();
                int id = salidaJson.has("id") ? salidaJson.get("id").getAsInt() : 0;
                String nombre = salidaJson.has("nombre") ? salidaJson.get("nombre").getAsString() : "SALIDA";
                int monto = salidaJson.has("montoEfecto") ? salidaJson.get("montoEfecto").getAsInt() : 200;
                casillas[id] = new Salida(id, nombre, monto);
            }
        }

        // Cargar PROPIEDADES
        if (json.has("propiedad") && json.get("propiedad").isJsonArray()) {
            JsonArray propiedades = json.getAsJsonArray("propiedad");
            for (int i = 0; i < propiedades.size(); i++) {
                JsonObject propJson = propiedades.get(i).getAsJsonObject();
                int id = propJson.has("id") ? propJson.get("id").getAsInt() : 0;
                String nombre = propJson.has("nombre") ? propJson.get("nombre").getAsString() : "Propiedad";
                int precio = propJson.has("precioCompra") ? propJson.get("precioCompra").getAsInt() : 0;
                int alquiler = propJson.has("alquilerBase") ? propJson.get("alquilerBase").getAsInt() : 0;
                casillas[id] = new Transporte(id, nombre, precio, alquiler, null);
            }
        }

        // Cargar SUERTE
        if (json.has("suerte") && json.get("suerte").isJsonArray()) {
            JsonArray suertes = json.getAsJsonArray("suerte");
            for (int i = 0; i < suertes.size(); i++) {
                JsonObject suerteJson = suertes.get(i).getAsJsonObject();
                int id = suerteJson.has("id") ? suerteJson.get("id").getAsInt() : 0;
                String nombre = suerteJson.has("nombre") ? suerteJson.get("nombre").getAsString() : "SUERTE";
                casillas[id] = new Suerte(id, nombre);
            }
        }

        // Cargar TRANSPORTES (estaciones)
        if (json.has("transporte") && json.get("transporte").isJsonArray()) {
            JsonArray transportes = json.getAsJsonArray("transporte");
            for (int i = 0; i < transportes.size(); i++) {
                JsonObject transporteJson = transportes.get(i).getAsJsonObject();
                int id = transporteJson.has("id") ? transporteJson.get("id").getAsInt() : 0;
                String nombre = transporteJson.has("nombre") ? transporteJson.get("nombre").getAsString() : "Transporte";
                int precio = transporteJson.has("precioCompra") ? transporteJson.get("precioCompra").getAsInt() : 0;
                int alquiler = transporteJson.has("alquilerBase") ? transporteJson.get("alquilerBase").getAsInt() : 0;
                casillas[id] = new Transporte(id, nombre, precio, alquiler, null);
            }
        }

        // Cargar CARCEL
        if (json.has("carcel") && json.get("carcel").isJsonArray()) {
            JsonArray carceles = json.getAsJsonArray("carcel");
            if (carceles.size() > 0) {
                JsonObject carcelJson = carceles.get(0).getAsJsonObject();
                int id = carcelJson.has("id") ? carcelJson.get("id").getAsInt() : 0;
                String nombre = carcelJson.has("nombre") ? carcelJson.get("nombre").getAsString() : "CARCEL";
                casillas[id] = new Carcel(id, nombre);
            }
        }

        // Cargar IR A CARCEL
        if (json.has("irACarcel") && json.get("irACarcel").isJsonArray()) {
            JsonArray irACarcel = json.getAsJsonArray("irACarcel");
            if (irACarcel.size() > 0) {
                JsonObject irCarcelJson = irACarcel.get(0).getAsJsonObject();
                int id = irCarcelJson.has("id") ? irCarcelJson.get("id").getAsInt() : 0;
                String nombre = irCarcelJson.has("nombre") ? irCarcelJson.get("nombre").getAsString() : "IR A LA CARCEL";
                int destino = irCarcelJson.has("destino") ? irCarcelJson.get("destino").getAsInt() : 5;
                casillas[id] = new IrACarcel(id, nombre, destino);
            }
        }

        // Cargar IMPUESTO
        if (json.has("impuesto") && json.get("impuesto").isJsonArray()) {
            JsonArray impuestos = json.getAsJsonArray("impuesto");
            if (impuestos.size() > 0) {
                JsonObject impuestoJson = impuestos.get(0).getAsJsonObject();
                int id = impuestoJson.has("id") ? impuestoJson.get("id").getAsInt() : 0;
                String nombre = impuestoJson.has("nombre") ? impuestoJson.get("nombre").getAsString() : "IMPUESTO";
                int monto = impuestoJson.has("montoEfecto") ? impuestoJson.get("montoEfecto").getAsInt() : 0;
                casillas[id] = new Impuesto(id, nombre, monto);
            }
        }

        // Rellenar casillas vacías (no debería haber)
        for (int i = 0; i < casillas.length; i++) {
            if (casillas[i] == null) {
                casillas[i] = new Salida(i, "Casilla " + i, 0);
            }
        }

        tablero.setCasillas(casillas);
    }

    public Tablero getTablero() {
        return tablero;
    }
}
