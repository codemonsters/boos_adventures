package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;
import es.codemonsters.boosadventures.game.Nivel;

public class PantallaPartida extends Pantalla {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Array<Nivel> todosLosNiveles;
    private boolean nivelEnCurso;

    public PantallaPartida(final MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        nivelEnCurso = false;
        todosLosNiveles = new Array<Nivel>();
        todosLosNiveles.add(new Nivel("test.lvl")); // TODO: Inicializar y declarar los niveles del juego en una sóla línea
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
        // Fixme: no es necesario generar una vez por frame el bitmap correspondiente al texto
        game.getBitmapFont().draw(game.getSpriteBatch(), "> ESTA ES LA PANTALLA DE JUEGO", 100, 160);

        game.getSpriteBatch().end();

    }

    /**
     * Reiniciamos el nivel, comenzando de nuevo con todos los jugadores (incluyendo tanto a los activos como a los que estaban en espera)
     */
    public void reiniciarNivel() {
        nivelEnCurso = false;
        game.incorporaJugadoresEnEspera();
        nivelEnCurso = true;
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
    public void conectaJugador(Jugador jugador) {
        // FIXME: En función de si la partida dentro de este nivel está en marcha deberíamos decidir si el jugador se añadido a la lista de jugadores activos o bien a la de jugadores en espera
        game.agregaJugadorEnEspera(jugador);
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
