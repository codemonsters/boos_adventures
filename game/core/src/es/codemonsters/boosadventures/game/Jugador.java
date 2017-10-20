package es.codemonsters.boosadventures.game;

import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;

public class Jugador {
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

}
