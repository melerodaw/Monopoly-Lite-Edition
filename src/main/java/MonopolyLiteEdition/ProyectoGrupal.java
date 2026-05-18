package MonopolyLiteEdition;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ProyectoGrupal {

    public static void main(String[] args) {
        // Ejemplo simple, estilo estudiante de primer curso:
        // - No usamos JsonObject ni deserializadores personalizados.
        // - Gson mapea el JSON a clases "raw" sencillas (TableroRaw/CasillaRaw).
        // - Después convertimos manualmente cada CasillaRaw a su subclase concreta.

        Gson gson = new Gson();

        try (FileReader fr = new FileReader("src/main/resources/tablero.json")) {
            System.out.println("Leyendo 'src/main/resources/tablero.json'...");

            // Parseamos el JSON a TableroRaw
            TableroRaw raw = gson.fromJson(fr, TableroRaw.class);
            if (raw == null || raw.tablero == null || raw.tablero.casillas == null) {
                System.out.println("No se pudo leer el JSON del tablero o está vacío.");
                return;
            }

            List<CasillaRaw> lista = raw.tablero.casillas;
            System.out.println("Casillas encontradas: " + lista.size());

            // Convertir cada registro a la subclase correspondiente
            Casilla[] casillas = new Casilla[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                CasillaRaw r = lista.get(i);
                String tipo = (r.tipo != null) ? r.tipo.toUpperCase() : "";
                switch (tipo) {
                    case "SALIDA":
                        casillas[i] = new Salida(r.id != null ? r.id : 0, r.nombre, r.montoEfecto != null ? r.montoEfecto : 0);
                        break;
                    case "PROPIEDAD":
                        casillas[i] = new Propiedad(r.id != null ? r.id : 0, r.nombre,
                                r.precioCompra != null ? r.precioCompra : 0,
                                r.alquilerBase != null ? r.alquilerBase : 0,
                                r.grupo,
                                null,
                                false);
                        break;
                    case "TRANSPORTE":
                        casillas[i] = new Transporte(r.id != null ? r.id : 0, r.nombre,
                                r.precioCompra != null ? r.precioCompra : 0,
                                r.alquilerBase != null ? r.alquilerBase : 0,
                                null);
                        break;
                    case "SUERTE":
                        casillas[i] = new Suerte(r.id != null ? r.id : 0, r.nombre);
                        break;
                    case "CARCEL":
                        casillas[i] = new Carcel(r.id != null ? r.id : 0, r.nombre);
                        break;
                    case "IR_A_LA_CARCEL":
                        casillas[i] = new IrACarcel(r.id != null ? r.id : 0, r.nombre, r.destino != null ? r.destino : 5);
                        break;
                    case "IMPUESTO":
                        casillas[i] = new Impuesto(r.id != null ? r.id : 0, r.nombre, r.montoEfecto != null ? r.montoEfecto : 0);
                        break;
                    default:
                        // Si no reconocemos el tipo, crear una casilla SALIDA por defecto
                        casillas[i] = new Salida(r.id != null ? r.id : 0, r.nombre, 0);
                        System.out.println("Advertencia: tipo desconocido '" + r.tipo + "' en posición " + i + ". Se creó una Salida por defecto.");
                        break;
                }
            }

            // Construimos el tablero y lo imprimimos
            Tablero tablero = new Tablero(casillas);
            System.out.println("\nTablero cargado. Listado de casillas:");
            for (Casilla c : tablero.getCasillas()) {
                System.out.println(c);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("JSON inválido: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }

    // POJOs simples para mapear el JSON sin usar JsonObject
    public static class TableroRaw {
        public TableroInner tablero;
    }

    public static class TableroInner {
        public Integer totalCasillas;
        public List<CasillaRaw> casillas;
    }

    public static class CasillaRaw {
        public Integer id;
        public String nombre;
        public String tipo;
        public Integer montoEfecto;
        public Integer precioCompra;
        public Integer alquilerBase;
        public String grupo;
        public Integer destino;
    }

}
