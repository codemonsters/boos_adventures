package es.codemonsters.boosadventures.game.dispositivosdejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;

public class DispositivoTeclado extends DispositivoDeJuego implements InputProcessor {
    private int key_code_up, key_code_down, key_code_left, key_code_right, key_code_button1, key_code_cancel;
    private MyGdxGame game;

    public DispositivoTeclado(int key_code_up, int key_code_down, int key_code_left, int key_code_right, int key_code_button1, int key_code_cancel, Jugador jugador, MyGdxGame game) {
        this.key_code_up = key_code_up;
        this.key_code_down = key_code_down;
        this.key_code_left = key_code_left;
        this.key_code_right = key_code_right;
        this.key_code_button1 = key_code_button1;
        this.key_code_cancel = key_code_cancel;
        this.game = game;
        this.setJugador(jugador);
    }

    @Override
    public void trasConectar() {
        game.getInputMultiplexer().addProcessor(this);
        super.trasConectar();
    }

    @Override
    public void desconectar() {
        game.getInputMultiplexer().removeProcessor(this);
        super.desconectar();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == key_code_button1) {
            game.getPantalla().alPresionarBoton1(getJugador());
        } else if (keycode == key_code_cancel) {
            game.getPantalla().alPresionarCancelar(getJugador());
        } else if (keycode == key_code_up) {
            game.getPantalla().alPresionarArriba(getJugador());
        } else if (keycode == key_code_down) {
            game.getPantalla().alPresionarAbajo(getJugador());
        } else if (keycode == key_code_left) {
            game.getPantalla().alPresionarIzquierda(getJugador());
        } else if (keycode == key_code_right) {
            game.getPantalla().alPresionarDerecha(getJugador());
        } else {
            Gdx.app.debug("DispositivoTeclado", "Pulsación de tecla ignorada (keycode=" + keycode + ")" );
            return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == key_code_button1) {
            game.getPantalla().alLiberarBoton1(getJugador());
        } else if (keycode == key_code_cancel) {
            game.getPantalla().alLiberarCancelar(getJugador());
        } else if (keycode == key_code_up) {
            game.getPantalla().alLiberarArriba(getJugador());
        } else if (keycode == key_code_down) {
            game.getPantalla().alLiberarAbajo(getJugador());
        } else if (keycode == key_code_left) {
            game.getPantalla().alLiberarIzquierda(getJugador());
        } else if (keycode == key_code_right) {
            game.getPantalla().alLiberarDerecha(getJugador());
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
