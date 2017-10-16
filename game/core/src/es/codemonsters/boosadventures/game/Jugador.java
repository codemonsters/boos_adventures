package es.codemonsters.boosadventures.game;

import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoDeJuego;

public class Jugador {
    private String nombre;
    private DispositivoDeJuego dispositivoDeJuego;

    public Jugador(String nombre, DispositivoDeJuego dispositivoDeJuego) {
        this.nombre = nombre;
        this.dispositivoDeJuego = dispositivoDeJuego;
    }
}
