package es.codemonsters.boosadventures.game.dispositivosdejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import es.codemonsters.boosadventures.game.Jugador;

public class DispositivoTeclado extends DispositivoDeJuego implements InputProcessor {
    private int key_code_up, key_code_down, key_code_left, key_code_right, key_code_button1, key_code_cancel;
    private Jugador jugador;
    private InputMultiplexer inputMultiplexer;

    public DispositivoTeclado(int key_code_up, int key_code_down, int key_code_left, int key_code_right, int key_code_button1, int key_code_cancel, InputMultiplexer inputMultiplexer) {
        this.key_code_up = key_code_up;
        this.key_code_down = key_code_down;
        this.key_code_left = key_code_left;
        this.key_code_right = key_code_right;
        this.key_code_button1 = key_code_button1;
        this.key_code_cancel = key_code_cancel;
        this.inputMultiplexer = inputMultiplexer;
    }

    @Override
    public void trasConectar() {
        inputMultiplexer.addProcessor(this);
        super.trasConectar();
    }

    @Override
    public void desconectar() {
        inputMultiplexer.removeProcessor(this);
        super.desconectar();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == key_code_button1) {
            jugador.alAccionarBoton1();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == key_code_button1) {
            jugador.alLiberarBoton1();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
