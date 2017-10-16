package es.codemonsters.boosadventures.game.dispositivosdejuego;

public class DispositivoTeclado implements DispositivoDeJuego {
    private int key_code_up, key_code_down, key_code_left, key_code_right, key_code_action, key_code_cancel;

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

    }

    @Override
    public void abajo() {

    }

    @Override
    public void izquierda() {

    }

    @Override
    public void derecha() {

    }

    @Override
    public void accion() {

    }

    @Override
    public void desconectarDispositivo() {

    }
}
