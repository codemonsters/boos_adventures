package es.codemonsters.boosadventures.game;

import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoDeJuego;

public class Jugador {
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }

}
