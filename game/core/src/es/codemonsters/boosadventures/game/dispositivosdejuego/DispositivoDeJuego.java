package es.codemonsters.boosadventures.game.dispositivosdejuego;

import com.badlogic.gdx.Gdx;

import es.codemonsters.boosadventures.game.Jugador;

public abstract class DispositivoDeJuego {
    private int id;
    private Jugador jugador;

    protected void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
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
