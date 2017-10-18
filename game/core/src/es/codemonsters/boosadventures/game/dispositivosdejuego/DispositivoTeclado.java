package es.codemonsters.boosadventures.game.dispositivosdejuego;

import es.codemonsters.boosadventures.game.Jugador;

public class DispositivoTeclado extends DispositivoDeJuego {
    private int key_code_up, key_code_down, key_code_left, key_code_right, key_code_button1, key_code_cancel;
    private Jugador jugador;

    public DispositivoTeclado(int key_code_up, int key_code_down, int key_code_left, int key_code_right, int key_code_button1, int key_code_cancel) {
        this.key_code_up = key_code_up;
        this.key_code_down = key_code_down;
        this.key_code_left = key_code_left;
        this.key_code_right = key_code_right;
        this.key_code_button1 = key_code_button1;
        this.key_code_cancel = key_code_cancel;
    }

    @Override
    public void accionaArriba() {
        // TODO: Implementar
    }

    @Override
    public void liberaArriba() {
        // TODO: Implementar
    }

    @Override
    public void accionaAbajo() {
        // TODO: Implementar
    }

    @Override
    public void liberaAbajo() {
        // TODO: Implementar
    }

    @Override
    public void accionaIzquierda() {
        // TODO: Implementar
    }

    @Override
    public void liberaIzquierda() {
        // TODO: Implementar
    }

    @Override
    public void accionaDerecha() {
        // TODO: Implementar
    }

    @Override
    public void liberaDerecha() {
        // TODO: Implementar
    }

    @Override
    public void accionaBoton1() {
        // TODO: Implementar
    }

    @Override
    public void liberaBoton1() {
        // TODO: Implementar
    }

}
