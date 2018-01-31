package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;

public class PantallaMenu extends Pantalla {
    public static final float FACTOR_ESCALA = 6;
    public static final float ANCHO_VIRTUAL = 44 * FACTOR_ESCALA;
    public static final float ALTO_VIRTUAL = 27 * FACTOR_ESCALA;
    private MyGdxGame game;
    //private OrthographicCamera camera;
    //private Viewport viewport;

    private Stage stage;
    private Table table;

    private float tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante;

    public PantallaMenu(final MyGdxGame game) {
        this.game = game;
        stage = new Stage(new FitViewport(ANCHO_VIRTUAL * FACTOR_ESCALA, ALTO_VIRTUAL * FACTOR_ESCALA));

        Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        uiSkin.getFont("commodore-64").getData().setScale(1f);
        table = new Table(uiSkin);
        table.setFillParent(true);
        table.background("dialog");

        /*
        TextArea textArea = new TextArea("HOLA", uiSkin);
        textArea.setDebug(true);
        table.add(textArea);
        table.setFillParent(true);
        table.setDebug(true);
        table.setPosition(0,0);
        stage.addActor(table);
        */

        TextArea textArea = new TextArea("HOLA\nHOLA2", uiSkin,"nobg");
        textArea.setDebug(true);
        textArea.setPosition(0 ,0);
        textArea.setSize(ANCHO_VIRTUAL * FACTOR_ESCALA, ALTO_VIRTUAL * FACTOR_ESCALA);
        textArea.setAlignment(Align.bottomLeft);
        stage.addActor(textArea);

        //Gdx.input.setInputProcessor(stage);
        //camera = new OrthographicCamera(ANCHO_VIRTUAL,ALTO_VIRTUAL);
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //viewport = new FitViewport(ANCHO_VIRTUAL, ALTO_VIRTUAL, camera);
        //viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //viewport.apply();
        //camera.position.set(camera.viewportWidth/2,camera.viewportHeight,0);
        Gdx.gl.glClearColor(0.0f, 0.5f, 0.0f, 1);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        tiempoDesdeUltimaVezQueSeMostroElCursorParpadeante = 0;
    }

    @Override
    public void render(float delta) {

        /*

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(0,0,0, ANCHO_VIRTUAL, ALTO_VIRTUAL,0, Color.BLUE,Color.RED);
        //Gdx.gl20.glLineWidth(1/camera.zoom);
        //                                                                viewport.apply();
        shapeRenderer.end();
        */
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        /*
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

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        */
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }

    @Override
    public void conectaJugador(Jugador jugador) {
        // Estamos en el menú, si un jugador se añade entonces pasa directamente a la lista de jugadores activos
        game.agregaJugadorActivo(jugador);
    }


    @Override
    public void alPresionarArriba(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alLiberarArriba(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alPresionarAbajo(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alLiberarAbajo(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alPresionarIzquierda(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alLiberarIzquierda(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alPresionarDerecha(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alLiberarDerecha(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alPresionarBoton1(Jugador jugador) {
        conectaJugador(jugador);
        // Estamos en el menú así que si cualquiera de los jugadores conectados pulsa el botón acción entonces cambiamos de pantalla para empezar el juego
        // TODO: Una idea mejor podría ser poner una cuenta atrás de 5 segundos para que otros jugadores pudiesen pulsar su boton1 e incorporarse todos juntos a la partida desde un inicio
        Gdx.app.debug("PantallaMenu", "Uno de los jugadores quiere empezar el juego");
        game.setPantalla(new PantallaJuego(game));
    }

    @Override
    public void alLiberarBoton1(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }

    @Override
    public void alPresionarCancelar(Jugador jugador) {
        // TODO: Estamos en el menú y el jugador a pulsado cancelar. Esto debería hacer que el jugador no se una a la partida o bien que se desconecte del servidor
    }

    @Override
    public void alLiberarCancelar(Jugador jugador) {
        // Estamos en el menú, ignoramos esta acción
    }


}
