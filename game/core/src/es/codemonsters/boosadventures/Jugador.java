package es.codemonsters.boosadventures;

import es.codemonsters.boosadventures.dispositivosdejuego.DispositivoDeJuego;

public class Jugador {
    private String nombre;
    private DispositivoDeJuego dispositivoDeJuego;

    public Jugador(String nombre, DispositivoDeJuego dispositivoDeJuego) {
        this.nombre = nombre;
        this.dispositivoDeJuego = dispositivoDeJuego;
    }
}
