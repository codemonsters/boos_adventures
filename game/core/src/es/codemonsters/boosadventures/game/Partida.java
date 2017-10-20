package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Partida {
    private Array<Jugador> jugadoresEnEspera;
    private Array<Jugador> jugadoresActivos;
    private Array<Nivel> niveles;
    private boolean nivelEnCurso;

    public Partida() {
        jugadoresEnEspera = new Array<Jugador>();
        jugadoresActivos = new Array<Jugador>();
        nivelEnCurso = false;
        niveles = new Array<Nivel>();
        niveles.add(new Nivel("test.lvl")); // TODO: Inicializar y declarar los niveles del juego en una sóla línea
    }

    /**
     * Reiniciamos la partida, comenzando de nuevo con todos los jugadores (incluyendo tanto a los activos como a los que estaban en espera)
     */
    public void reiniciarPartida() {
        nivelEnCurso = false;
        for (int i = 0; i < jugadoresEnEspera.size; i++) {
            Jugador jugadorEnEspera = jugadoresEnEspera.get(i);
            jugadoresActivos.add(jugadorEnEspera);
            jugadoresEnEspera.removeIndex(i);
        }
        nivelEnCurso = true;
    }



    public void ConectaJugador(Jugador jugador) {
        if (jugadoresActivos.contains(jugador, false) || jugadoresEnEspera.contains(jugador, false)) {
            Gdx.app.debug("Partida", "El jugador ya se había añadido, ignorando esta nueva solicitud");
        } else {
            if (nivelEnCurso) {
                jugadoresEnEspera.add(jugador);
            } else {
                jugadoresActivos.add(jugador);
            }
        }
    }
}
