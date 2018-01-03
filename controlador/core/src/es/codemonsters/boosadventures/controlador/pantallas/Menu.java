package es.codemonsters.boosadventures.controlador.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;

import es.codemonsters.boosadventures.controlador.MyGdxGame;


public class Menu implements Screen
{
    private MyGdxGame game;
    MyInputProcessor processor;
    SpriteBatch batch;
    public String name;
    BitmapFont font;

    public Menu(MyGdxGame game)
    {
        name = "";
        this.game = game;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        batch = new SpriteBatch();
        processor = new MyInputProcessor();
        Gdx.input.setInputProcessor(processor);
    }



    public void show() {}


    public void render(float delta)
    {
        name = processor.name;
        if (processor.avanzar)
            game.setPantalla(new Controles(game, name));
        Gdx.gl.glClearColor(0.0F, 1.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        batch.begin();
        font.draw(batch, "Dame tu nombre: " + name, 10.0F, Gdx.graphics.getHeight() - 20);
        font.draw(batch, "Pulsa intro cuando estés listo!", 10.0F, Gdx.graphics.getHeight() - 40);
        batch.end();
    }




    public void resize(int width, int height) {}




    public void pause() {}



    public void resume() {}



    public void hide() {}



    public void dispose()
    {
        batch.dispose();
        font.dispose();
    }
}
class MyInputProcessor
        implements InputProcessor
{
    public String name = "";
    public boolean avanzar = false;
    public boolean keyPressed;

    MyInputProcessor() {}

    public boolean keyDown(int keycode) {
        if ((keycode != 67) && (keycode != 62) && (Input.Keys.toString(keycode).length() == 1)) {
            String letra = Input.Keys.toString(keycode);
            if (name.length() >= 1)
                letra = letra.toLowerCase();
            name += letra;
        }
        else if (keycode == 67) {
            if (name.length() > 0) {
                name = name.substring(0, name.length() - 1);
            }
        } else if (keycode == 62) {
            name += " ";
        } else if (keycode == 66) {
            avanzar = true;
        }
        return false;
    }


    public boolean keyUp(int keycode)
    {
        return false;
    }

    public boolean keyTyped(char character)
    {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    public boolean scrolled(int amount)
    {
        return false;
    }
}
