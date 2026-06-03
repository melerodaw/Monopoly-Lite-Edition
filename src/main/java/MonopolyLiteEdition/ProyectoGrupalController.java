package MonopolyLiteEdition;

public class ProyectoGrupalController {
    private final Juego juego;
    private final Dado dado;

    public ProyectoGrupalController() {
        this.juego = new Juego();
        this.dado = new Dado();
    }

    public int lanzarDado() {
        return dado.generarAleatorio();
    }

    public String obtenerEstado() {
        Jugador jugadorActual = juego.getJugadorActual();
        String jugador = (jugadorActual != null) ? jugadorActual.getNombre() : "Sin jugadores";
        return "Turno actual: " + juego.getTurnoActual() + " | Jugador: " + jugador + " | Estado: " + juego.getEstadoPartida();
    }
}


