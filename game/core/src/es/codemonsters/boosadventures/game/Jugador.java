package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.utils.Disposable;

import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;

public class Jugador implements Disposable {
    private String nombre;
    private ObjetoJugador objetoJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setObjetoJugador(ObjetoJugador objetoJugador) {
        this.objetoJugador = objetoJugador;
    }

    public ObjetoJugador getObjetoJugador() {
        return objetoJugador;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    @Override
    public void dispose() {
        if (getObjetoJugador() != null) {
            getObjetoJugador().dispose();
        }
    }
}
