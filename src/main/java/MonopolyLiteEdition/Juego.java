package MonopolyLiteEdition;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private List<Jugador> listaJugadores;
    private int turnoActual;
    private String estadoPartida;

    public Juego() {
        this.listaJugadores = new ArrayList<>();
        this.turnoActual = 0;
        this.estadoPartida = "NO_INICIADA";
    }

    public Juego(List<Jugador> listaJugadores, int turnoActual, String estadoPartida) {
        this.listaJugadores = (listaJugadores != null) ? listaJugadores : new ArrayList<>();
        this.turnoActual = turnoActual;
        this.estadoPartida = estadoPartida;
    }

    public Juego(Juego j) {
        this.listaJugadores = new ArrayList<>(j.listaJugadores);
        this.turnoActual = j.turnoActual;
        this.estadoPartida = j.estadoPartida;
    }

    public List<Jugador> getListaJugadores() { return listaJugadores; }
    public int getTurnoActual() { return turnoActual; }
    public String getEstadoPartida() { return estadoPartida; }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = (listaJugadores != null) ? listaJugadores : new ArrayList<>();
    }

    public void setTurnoActual(int turnoActual) { this.turnoActual = turnoActual; }
    public void setEstadoPartida(String estadoPartida) { this.estadoPartida = estadoPartida; }

    public void iniciarPartida() {
        this.estadoPartida = "EN_CURSO";
        this.turnoActual = 0;
    }

    public void siguienteTurno() {
        if (listaJugadores == null || listaJugadores.isEmpty()) return;
        this.turnoActual = (this.turnoActual + 1) % listaJugadores.size();
    }

    public Jugador getJugadorActual() {
        if (listaJugadores == null || listaJugadores.isEmpty()) return null;
        if (turnoActual < 0 || turnoActual >= listaJugadores.size()) return null;
        return listaJugadores.get(turnoActual);
    }

    public Jugador verificarGanador() {
        // Ejemplo simple: ganador = Ãºltimo con saldo > 0
        if (listaJugadores == null || listaJugadores.isEmpty()) return null;

        Jugador candidato = null;
        int vivos = 0;
        for (Jugador j : listaJugadores) {
            if (j.getSaldo() > 0) {
                vivos++;
                candidato = j;
            }
        }
        return (vivos == 1) ? candidato : null;
    }
    public void agregarJugador(Jugador jugador) {
        if (jugador != null && listaJugadores != null) {
            listaJugadores.add(jugador);
        }
    }
    @Override
    public String toString() {
        return "Juego{" +
                "jugadores=" + (listaJugadores != null ? listaJugadores.size() : 0) +
                ", turnoActual=" + turnoActual +
                ", estadoPartida='" + estadoPartida + '\'' +
                '}';
    }
}
