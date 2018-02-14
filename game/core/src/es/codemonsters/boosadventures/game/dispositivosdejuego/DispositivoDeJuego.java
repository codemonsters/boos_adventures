package es.codemonsters.boosadventures.game.dispositivosdejuego;

import com.badlogic.gdx.Gdx;

import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;

public abstract class DispositivoDeJuego {
    private int id;
    private Jugador jugador;
    private MyGdxGame game;

    protected void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    protected void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void trasConectar() {
        Gdx.app.debug("DispositivoDeJuego", "Dispositivo de juego conectado (id = " + getId() + ")");
    }

    public void desconectar() {
        Gdx.app.debug("DispositivoDeJuego", "Dispositivo de juego desconectado (id = " + getId() + ")");
    }

    @Override
    public String toString() {
        return "id = " + getId();
    }
}
