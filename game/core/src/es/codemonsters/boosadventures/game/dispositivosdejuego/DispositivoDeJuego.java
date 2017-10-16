package es.codemonsters.boosadventures.game.dispositivosdejuego;

import com.badlogic.gdx.Input;

public interface DispositivoDeJuego {
    public void arriba();
    public void abajo();
    public void izquierda();
    public void derecha();
    public void accion();
    public void desconectarDispositivo();
}
