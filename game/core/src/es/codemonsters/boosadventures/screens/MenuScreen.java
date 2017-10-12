package es.codemonsters.boosadventures.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.codemonsters.boosadventures.MyGdxGame;

public class MenuScreen implements Screen {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    public MenuScreen(final MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        game.getBitmapFont().draw(game.getSpriteBatch(), "Welcome", 100, 150);  // Fixme: no es necesario generar una vez por frame el bitmap correspondiente al texto
        game.getSpriteBatch().end();

        /*
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }*/
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }
}
