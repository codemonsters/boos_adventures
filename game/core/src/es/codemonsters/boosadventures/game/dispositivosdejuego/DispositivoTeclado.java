package es.codemonsters.boosadventures.game.dispositivosdejuego;

import es.codemonsters.boosadventures.game.Jugador;

public class DispositivoTeclado extends DispositivoDeJuego {
    private int key_code_up, key_code_down, key_code_left, key_code_right, key_code_action, key_code_cancel;
    private Jugador jugador;

    public DispositivoTeclado(int key_code_up, int key_code_down, int key_code_left, int key_code_right, int key_code_action, int key_code_cancel) {
        this.key_code_up = key_code_up;
        this.key_code_down = key_code_down;
        this.key_code_left = key_code_left;
        this.key_code_right = key_code_right;
        this.key_code_action = key_code_action;
        this.key_code_cancel = key_code_cancel;
    }

    @Override
    public void arriba() {
        // TODO: Implementar este método
    }

    @Override
    public void abajo() {
        // TODO: Implementar este método
    }

    @Override
    public void izquierda() {
        // TODO: Implementar este método
    }

    @Override
    public void derecha() {
        // TODO: Implementar este método
    }

    @Override
    public void accion() {
        // TODO: Implementar este método
    }

    @Override
    public void desconectar() {
        // Nada que hacer tras desconectar en el caso del teclado
    }

}
