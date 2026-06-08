package MonopolyLiteEdition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProyectoGrupalController {
    private final Juego juego;
    private final Dado dado;
    private Tablero tablero;
    private ReglasTirada reglas;

    public ProyectoGrupalController() {
        this.juego = new Juego();
        this.dado = new Dado();
    }

    /**
     * Inicializa el juego: carga tablero y reglas desde JSON, crea jugadores.
     */
    public List<Jugador> iniciarJuego(int numJugadores) {
        cargarRecursos();

        // Crear jugadores con dinero inicial
        String[] colores = {"Rojo", "Azul", "Verde", "Naranja"};
        for (int i = 0; i < Math.min(numJugadores, 4); i++) {
            Jugador j = new Jugador(
                colores[i],
                reglas != null ? reglas.getDineroInicial() : 1500,
                0,
                false,
                0,
                new ArrayList<>()
            );
            juego.agregarJugador(j);
        }

        juego.iniciarPartida();
        return juego.getListaJugadores();
    }

    /**
     * Lanza el dado, mueve al jugador y devuelve el resultado.
     */
    public TurnoResultado lanzarDado() {
        Jugador jugador = juego.getJugadorActual();
        if (jugador == null) {
            return new TurnoResultado(0, 0, "Error", "Sin jugador activo", false, false);
        }

        int posicionAnterior = jugador.getPosicionActual();
        int resultado = dado.generarAleatorio();
        int nuevaPosicion = tablero.calcularNuevaPosicion(posicionAnterior, resultado);

        // Detectar si cruzó SALIDA (da la vuelta completa)
        boolean cruzoSalida = (tablero != null && tablero.getCasillas() != null)
            ? (posicionAnterior + resultado >= tablero.getCasillas().length)
            : (posicionAnterior + resultado >= 20);

        // Actualizar posición (algunas casillas pueden cambiarla de nuevo al ejecutar acción)
        jugador.setPosicionActual(nuevaPosicion);

        Casilla casilla = (tablero != null) ? tablero.obtenerCasillas(nuevaPosicion) : null;
        if (casilla == null) {
            return new TurnoResultado(resultado, nuevaPosicion, "Error", "Casilla no válida", false, false);
        }

        String descripcion = "Llegaste a " + casilla.getNombre();
        boolean puedoComprar = false;
        boolean debeTerminar = true;

        // Si cruzó la salida, aplicar su efecto (buscar la casilla Salida y ejecutar)
        if (cruzoSalida) {
            if (tablero != null && tablero.getCasillas() != null) {
                for (Casilla c : tablero.getCasillas()) {
                    if (c instanceof Salida) {
                        ((Salida) c).ejecutarAccion(jugador);
                        descripcion = "Pasaste por SALIDA, ganaste $" + ((Salida) c).getMontoEfecto();
                        break;
                    }
                }
            } else {
                // Valor por defecto
                jugador.setSaldo(jugador.getSaldo() + 200);
                descripcion = "Pasaste por SALIDA, ganaste $200";
            }
        }

        // Determinar si puede comprar y generar descripción según tipo de casilla
        if (casilla instanceof Propiedad) {
            Propiedad prop = (Propiedad) casilla;
            if (prop.getPropietario() == null) {
                puedoComprar = true;
                descripcion = "Propiedad disponible: " + prop.getNombre() + " ($" + prop.getPrecioCompra() + ")";
            } else if (prop.getPropietario() == jugador) {
                descripcion = "Ya posees " + prop.getNombre();
            } else {
                int renta = prop.getRentaActual();
                jugador.setSaldo(jugador.getSaldo() - renta);
                prop.getPropietario().setSaldo(prop.getPropietario().getSaldo() + renta);
                descripcion = "Pagaste $" + renta + " de alquiler a " + prop.getPropietario().getNombre();
            }
        } else if (casilla instanceof Transporte) {
            Transporte transp = (Transporte) casilla;
            if (transp.getPropietario() == null) {
                puedoComprar = true;
                descripcion = "Transporte disponible: " + transp.getNombre() + " ($" + transp.getPrecioCompra() + ")";
            } else if (transp.getPropietario() != jugador) {
                int renta = transp.getAlquilerBase();
                jugador.setSaldo(jugador.getSaldo() - renta);
                transp.getPropietario().setSaldo(transp.getPropietario().getSaldo() + renta);
                descripcion = "Pagaste $" + renta + " de transporte a " + transp.getPropietario().getNombre();
            }
        } else if (casilla instanceof Salida) {
            // Si la casilla final es SALIDA, ejecutar su acción (podría repetir el cobro si ya se aplicó al cruzar)
            Salida s = (Salida) casilla;
            s.ejecutarAccion(jugador);
            descripcion = "Pasaste/Caíste en SALIDA, ganaste $" + s.getMontoEfecto();
        } else if (casilla instanceof Impuesto) {
            Impuesto impuesto = (Impuesto) casilla;
            jugador.setSaldo(jugador.getSaldo() - impuesto.getMontoEfecto());
            descripcion = "Pagaste $" + impuesto.getMontoEfecto() + " de impuesto";
        } else if (casilla instanceof IrACarcel) {
            // Ejecutar acción de ir a la cárcel (cambia la posición del jugador)
            IrACarcel ir = (IrACarcel) casilla;
            ir.ejecutarAccion(jugador);
            descripcion = "¡Vas a la cárcel! Ahora en casilla " + jugador.getPosicionActual();
            puedoComprar = false;
        } else if (casilla instanceof Suerte) {
            Suerte s = (Suerte) casilla;
            // Intentar usar método que devuelve descripción del efecto
            try {
                String descCarta = s.sacarYAplicarCarta(jugador);
                descripcion = "Carta: " + descCarta;
            } catch (Exception ex) {
                // Fallback: ejecutar la acción sin descripción detallada
                s.ejecutarAccion(jugador);
                descripcion = "Has sacado una carta de Suerte";
            }
        } else {
            descripcion = "Efecto de " + casilla.getNombre();
        }

        return new TurnoResultado(resultado, nuevaPosicion, casilla.getNombre(), descripcion, puedoComprar, debeTerminar);
    }

    /**
     * Compra la propiedad actual para el jugador actual.
     */
    public String comprarPropiedad() {
        Jugador jugador = juego.getJugadorActual();
        if (jugador == null) return "Error: Sin jugador activo";

        Casilla casilla = tablero.obtenerCasillas(jugador.getPosicionActual());
        if (casilla == null) return "Error: Casilla inválida";

        if (casilla instanceof Propiedad) {
            Propiedad prop = (Propiedad) casilla;
            if (prop.getPropietario() != null) return "Error: Ya tiene dueño";
            if (jugador.getSaldo() < prop.getPrecioCompra()) return "Error: Dinero insuficiente";

            jugador.setSaldo(jugador.getSaldo() - prop.getPrecioCompra());
            prop.setPropietario(jugador);
            jugador.agregarPropiedad(prop);
            return "Compraste " + prop.getNombre() + " por $" + prop.getPrecioCompra();
        }

        if (casilla instanceof Transporte) {
            Transporte transp = (Transporte) casilla;
            if (transp.getPropietario() != null) return "Error: Ya tiene dueño";
            if (jugador.getSaldo() < transp.getPrecioCompra()) return "Error: Dinero insuficiente";

            jugador.setSaldo(jugador.getSaldo() - transp.getPrecioCompra());
            transp.setPropietario(jugador);
            jugador.agregarPropiedad(transp);
            return "Compraste " + transp.getNombre() + " por $" + transp.getPrecioCompra();
        }

        return "Error: Casilla no comprable";
    }

    /**
     * Avanza al siguiente turno.
     */
    public Jugador terminarTurno() {
        juego.siguienteTurno();
        return juego.getJugadorActual();
    }

    public Jugador getJugadorActual() {
        return juego.getJugadorActual();
    }

    public List<Casilla> getCasillasDelTablero() {
        List<Casilla> casillas = new ArrayList<>();
        if (tablero != null && tablero.getCasillas() != null) {
            for (Casilla c : tablero.getCasillas()) {
                casillas.add(c);
            }
        }
        return casillas;
    }

    private void cargarRecursos() {
        try {
            String baseDir = "src/main/resources/";
            Gson gson = new Gson();

            // Cargar tablero
            String tableroJson = new String(
                Files.readAllBytes(Paths.get(baseDir + "tablero.json"))
            );
            JsonObject tableroObj = gson.fromJson(tableroJson, JsonObject.class);
            TableroManager manager = new TableroManager();
            manager.cargarTableroDesdeJson(tableroObj);
            tablero = manager.getTablero();

            // Cargar reglas (si existen)
            try {
                String reglasJson = new String(
                    Files.readAllBytes(Paths.get(baseDir + "reglas.json"))
                );
                JsonObject reglasObj = gson.fromJson(reglasJson, JsonObject.class);
                reglas = new ReglasTirada();
                reglas.cargarDesdeJson(reglasObj);
            } catch (Exception e) {
                reglas = new ReglasTirada(); // Usar valores por defecto
            }
            // Cargar baraja de suerte y asignarla a casillas Suerte
            try {
                String cartasJson = new String(
                    Files.readAllBytes(Paths.get(baseDir + "cartas_suerte.json"))
                );
                JsonObject cartasObj = gson.fromJson(cartasJson, JsonObject.class);
                Baraja baraja = new Baraja();
                baraja.cargarDesdeJson(cartasObj);

                if (tablero != null && tablero.getCasillas() != null) {
                    for (int i = 0; i < tablero.getCasillas().length; i++) {
                        Casilla c = tablero.obtenerCasillas(i);
                        if (c instanceof Suerte) {
                            ((Suerte) c).setBaraja(baraja);
                        }
                    }
                }
            } catch (Exception e) {
                // No hay baraja; no es crítico para iniciar
            }
        } catch (IOException e) {
            System.err.println("Error al cargar recursos: " + e.getMessage());
        }
    }

    /**
     * Clase interna para encapsular el resultado de un turno.
     */
    public static class TurnoResultado {
        public final int valorDado;
        public final int casillaNueva;
        public final String nombreCasilla;
        public final String descripcionAccion;
        public final boolean puedoComprar;
        public final boolean debeTerminarTurno;

        public TurnoResultado(
            int valorDado,
            int casillaNueva,
            String nombreCasilla,
            String descripcionAccion,
            boolean puedoComprar,
            boolean debeTerminarTurno
        ) {
            this.valorDado = valorDado;
            this.casillaNueva = casillaNueva;
            this.nombreCasilla = nombreCasilla;
            this.descripcionAccion = descripcionAccion;
            this.puedoComprar = puedoComprar;
            this.debeTerminarTurno = debeTerminarTurno;
        }
    }
}


