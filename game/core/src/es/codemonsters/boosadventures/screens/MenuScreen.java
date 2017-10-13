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
    private float tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante;

    public MenuScreen(final MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante = 0;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        // Fixme: no es necesario generar una vez por frame el bitmap correspondiente al texto
        game.getBitmapFont().draw(game.getSpriteBatch(), "**** " + MyGdxGame.nombreDelJuego.toUpperCase() + " VERSION " + MyGdxGame.versionDelJuego + " ****", 100, 190);
        game.getBitmapFont().draw(game.getSpriteBatch(), "> PREPARADO", 100, 160);
        game.getBitmapFont().draw(game.getSpriteBatch(), "> INSTALA LA APLICACION ANDROID PARA CONECTARTE AL JUEGO", 100, 130);
        game.getBitmapFont().draw(game.getSpriteBatch(), "> ESPERANDO JUGADORES...", 100, 100);
        game.getBitmapFont().draw(game.getSpriteBatch(), ">", 100, 70);

        // Cursor parpadeando
        tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante += delta;
        if (tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante>0.6) {
            game.getBitmapFont().draw(game.getSpriteBatch(), "_", 117, 72);
            if (tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante > 1.2) {
                tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante = 0;
            }
        }
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
