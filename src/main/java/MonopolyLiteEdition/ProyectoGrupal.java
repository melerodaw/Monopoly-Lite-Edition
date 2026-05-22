package MonopolyLiteEdition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProyectoGrupal {
    private static Juego juego;
    private static Tablero tablero;
    private static Baraja baraja;
    private static ReglasTirada reglas;
    private static Dado dado;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   BIENVENIDO A MONOPOLY LITE EDITION   ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Cargar recursos JSON
        if (!cargarRecursos()) {
            System.out.println("Error al cargar los recursos del juego.");
            return;
        }

        // Crear juego
        juego = new Juego();

        // Agregar jugadores
        agregarJugadores();

        if (juego.getListaJugadores().size() < 2) {
            System.out.println("Se necesitan al menos 2 jugadores para jugar.");
            return;
        }

        // Iniciar partida
        System.out.println("\n¡COMIENZA LA PARTIDA!\n");
        juego.iniciarPartida();

        // Loop principal
        bucleJuego();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       ¡FIN DE LA PARTIDA!              ║");
        System.out.println("╚════════════════════════════════════════╝");
        scanner.close();
    }

    private static boolean cargarRecursos() {
        try {
            String baseDir = "src/main/resources/";
            Gson gson = new Gson();

            // Cargar tablero
            String tableroJson = new String(Files.readAllBytes(Paths.get(baseDir + "tablero.json")));
            JsonObject tableroObj = gson.fromJson(tableroJson, JsonObject.class);
            TableroManager manager = new TableroManager();
            manager.cargarTableroDesdeJson(tableroObj);
            tablero = manager.getTablero();
            System.out.println("✓ Tablero cargado");

            // Cargar cartas de suerte
            String cartasJson = new String(Files.readAllBytes(Paths.get(baseDir + "cartas_suerte.json")));
            JsonObject cartasObj = gson.fromJson(cartasJson, JsonObject.class);
            baraja = new Baraja();
            baraja.cargarDesdeJson(cartasObj);
            System.out.println("✓ Baraja de suerte cargada (" + baraja.getTamaño() + " cartas)");

            // Asignar baraja a casillas de suerte
            for (Casilla casilla : tablero.getCasillas()) {
                if (casilla instanceof Suerte) {
                    ((Suerte) casilla).setBaraja(baraja);
                }
            }

            // Cargar reglas
            String reglasJson = new String(Files.readAllBytes(Paths.get(baseDir + "reglas.json")));
            JsonObject reglasObj = gson.fromJson(reglasJson, JsonObject.class);
            reglas = new ReglasTirada();
            reglas.cargarDesdeJson(reglasObj);
            System.out.println("✓ Reglas cargadas");

            // Inicializar dado
            dado = new Dado();

            return true;
        } catch (IOException e) {
            System.out.println("Error al cargar archivos JSON: " + e.getMessage());
            return false;
        }
    }

    private static void agregarJugadores() {
        System.out.println("¿Cuántos jugadores participarán? (2-4): ");
        int numJugadores = 0;

        try {
            numJugadores = Integer.parseInt(scanner.nextLine());
            if (numJugadores < 2 || numJugadores > 4) {
                numJugadores = 2;
            }
        } catch (NumberFormatException e) {
            numJugadores = 2;
        }

        for (int i = 1; i <= numJugadores; i++) {
            System.out.println("Nombre del jugador " + i + ": ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                nombre = "Jugador " + i;
            }

            Jugador jugador = new Jugador(nombre, reglas.getDineroInicial(), 0, false, 0, new ArrayList<>());
            juego.agregarJugador(jugador);
            System.out.println("  ✓ " + nombre + " se unió con " + reglas.getDineroInicial() + " €");
        }
    }

    private static void bucleJuego() {
        int turnoCount = 0;
        int maxTurnos = 100; // Límite de turnos para evitar loop infinito

        while (turnoCount < maxTurnos) {
            Jugador jugadorActual = juego.getJugadorActual();

            // Verificar si solo queda un jugador
            if (jugadoresActivos() <= 1) {
                Jugador ganador = juego.verificarGanador();
                if (ganador != null) {
                    System.out.println("\n¡" + ganador.getNombre() + " HA GANADO LA PARTIDA!");
                    System.out.println("   Saldo final: " + ganador.getSaldo() + " €");
                }
                return;
            }

            System.out.println("\n┌─ TURNO " + (turnoCount + 1) + " ─────────────────────────┐");
            System.out.println("│ Jugador: " + jugadorActual.getNombre());
            System.out.println("│ Posición: " + jugadorActual.getPosicionActual() + " | Saldo: $" + jugadorActual.getSaldo());
            System.out.println("└────────────────────────────────────┘");

            // Manejo de cárcel
            if (jugadorActual.getEstaEnCarcel()) {
                manejarCarcel(jugadorActual);
            } else {
                // Tirar dados
                int tirada = dado.generarAleatorio();
                System.out.println( jugadorActual.getNombre() + " tiró un " + tirada);

                // Avanzar posición
                int nuevaPosicion = tablero.calcularNuevaPosicion(jugadorActual.getPosicionActual(), tirada);
                jugadorActual.setPosicionActual(nuevaPosicion);

                // Executar acción de la casilla
                Casilla casilla = tablero.obtenerCasillas(nuevaPosicion);
                if (casilla != null) {
                    System.out.println("Llegó a: " + casilla.getNombre());
                    casilla.ejecutarAccion(jugadorActual);
                }

                // Verificar si saldo negativo = eliminado
                if (jugadorActual.getSaldo() < 0) {
                    System.out.println(jugadorActual.getNombre() + " está en quiebra.");
                }
            }

            // Pausa entre turnos
            System.out.println("Presiona ENTER para continuar...");
            scanner.nextLine();

            // Siguiente turno
            juego.siguienteTurno();
            turnoCount++;
        }

        System.out.println("\nSe alcanzó el límite de turnos.");
    }

    private static void manejarCarcel(Jugador jugador) {
        jugador.setTurnosRestantesCarcel(jugador.getTurnosRestantesCarcel() - 1);
        System.out.println(jugador.getNombre() + " está en cárcel. Turnos restantes: " + Math.max(0, jugador.getTurnosRestantesCarcel()));

        if (jugador.getTurnosRestantesCarcel() <= 0) {
            jugador.setEstaEnCarcel(false);
            System.out.println("✓ ¡" + jugador.getNombre() + " salió de la cárcel!");
        }
    }

    private static int jugadoresActivos() {
        int activos = 0;
        for (Jugador j : juego.getListaJugadores()) {
            if (j.getSaldo() >= 0) {
                activos++;
            }
        }
        return activos;
    }
}