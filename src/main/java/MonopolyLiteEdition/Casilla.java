package MonopolyLiteEdition;

import com.google.gson.JsonObject;

public abstract class Casilla {

    private int id;
    private String nombre;

    public Casilla() {
        this.id = 0;
        this.nombre = "Sin nombre";
    }

    public Casilla(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Casilla(Casilla c) {
        this.id = c.id;
        this.nombre = c.nombre;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract void ejecutarAccion(Jugador j);

    public static Casilla fromJson(JsonObject obj) {
        if (obj == null) {
            return null;
        }

        int id = leerEntero(obj, "id", 0);
        String nombre = leerCadena(obj, "nombre", "Sin nombre");
        String tipo = leerCadena(obj, "tipo", "").toUpperCase();

        switch (tipo) {
            case "SALIDA":
                return new Salida(id, nombre, leerEntero(obj, "montoEfecto", 0));
            case "PROPIEDAD":
                return new Propiedad(id, nombre, leerEntero(obj, "precioCompra", 0), leerEntero(obj, "alquilerBase", 0), null, false);
            case "TRANSPORTE":
                return new Transporte(id, nombre, leerEntero(obj, "precioCompra", 0), leerEntero(obj, "alquilerBase", 0), null);
            case "SUERTE":
                return new Suerte(id, nombre);
            case "CARCEL":
                return new Carcel(id, nombre);
            case "IR_A_LA_CARCEL":
                return new IrACarcel(id, nombre, leerEntero(obj, "destino", 5));
            case "IMPUESTO":
                return new Impuesto(id, nombre, leerEntero(obj, "montoEfecto", 0));
            default:
                throw new IllegalArgumentException("Tipo de casilla no soportado: " + tipo);
        }
    }

    private static int leerEntero(JsonObject obj, String campo, int valorDefecto) {
        return (obj.has(campo) && !obj.get(campo).isJsonNull()) ? obj.get(campo).getAsInt() : valorDefecto;
    }

    private static String leerCadena(JsonObject obj, String campo, String valorDefecto) {
        return (obj.has(campo) && !obj.get(campo).isJsonNull()) ? obj.get(campo).getAsString() : valorDefecto;
    }

    @Override
    public String toString() {
        return "Casilla [id=" + id + ", nombre=" + nombre + "]";
    }
}
