package es.codemonsters.boosadventures.game.dispositivosdejuego;

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

    public abstract void arriba();

    public abstract void abajo();

    public abstract void izquierda();

    public abstract void derecha();

    public abstract void accion();

    public abstract void desconectar();

    @Override
    public String toString() {
        return "Id = " + getId() + " Jugador = " + getJugador();
    }
}
