package es.codemonsters.boosadventures.game.dispositivosdejuego;

import es.codemonsters.boosadventures.game.Jugador;

public interface DispositivoDeJuego {
    public void setJugador(Jugador jugador);
    public Jugador getJugador();
    public void arriba();
    public void abajo();
    public void izquierda();
    public void derecha();
    public void accion();
    public void desconectarDispositivo();
}
