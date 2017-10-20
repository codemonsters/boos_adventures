package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;

public class PantallaPartida extends Pantalla {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante;

    public PantallaPartida(final MyGdxGame game) {
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
        game.getBitmapFont().draw(game.getSpriteBatch(), "> ESTA ES LA PANTALLA DE JUEGO", 100, 160);

        // Cursor parpadeando
        tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante += delta;
        if (tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante>0.6) {
            game.getBitmapFont().draw(game.getSpriteBatch(), "_", 117, 72);
            if (tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante > 1.2) {
                tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante = 0;
            }
        }
        game.getSpriteBatch().end();

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

    @Override
    public void alPresionarArriba(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alLiberarArriba(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alPresionarAbajo(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alLiberarAbajo(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alPresionarIzquierda(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alLiberarIzquierda(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alPresionarDerecha(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alLiberarDerecha(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alPresionarBoton1(Jugador jugador) {
        // TODO Auto-generated method stub
        Gdx.app.debug("PantallaPartida", "Uno de los jugadores ha presionado su boton1");
    }

    @Override
    public void alLiberarBoton1(Jugador jugador) {
        // TODO Auto-generated method stub
    }


}
