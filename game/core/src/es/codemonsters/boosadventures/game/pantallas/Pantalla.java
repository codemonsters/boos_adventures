package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Screen;

import es.codemonsters.boosadventures.game.InputProcessorJugadores;
import es.codemonsters.boosadventures.game.Jugador;

public abstract class Pantalla implements Screen, InputProcessorJugadores {
    public abstract void conectaJugador(Jugador nuevoJugador);
}
