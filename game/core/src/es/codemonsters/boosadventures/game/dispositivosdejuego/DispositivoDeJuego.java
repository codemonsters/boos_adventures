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

    public abstract void accionaArriba();

    public abstract void liberaArriba();

    public abstract void accionaAbajo();

    public abstract void liberaAbajo();

    public abstract void accionaIzquierda();

    public abstract void liberaIzquierda();

    public abstract void accionaDerecha();

    public abstract void liberaDerecha();

    public abstract void accionaBoton1();

    public abstract void liberaBoton1();

    public void desconectar() {
        Gdx.app.debug("DispoaitivoDeJuego", "Desconectando dispositivo de juego (id = " + getId() + ")");
    }

    @Override
    public String toString() {
        return "Id = " + getId() + " Jugador = " + getJugador();
    }
}
