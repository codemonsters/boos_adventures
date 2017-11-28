package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;
import es.codemonsters.boosadventures.game.Nivel;
import es.codemonsters.boosadventures.game.objetosdeljuego.Bloque;
import es.codemonsters.boosadventures.game.objetosdeljuego.LimitesNivel;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;

public class PantallaJuego extends Pantalla {

    public static final int ANCHO_DEL_MUNDO = 44;   // Ancho del mundo (en metros)
    public static final int ALTO_DEL_MUNDO = 27;    // Alto del mundo (en metros)
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Array<Nivel> todosLosNiveles;
    private boolean nivelEnCurso;

    private Vector2 spawnPos = new Vector2();
    Sprite sprite;
    private World world;
    private Box2DDebugRenderer box2DDebugRendered;
    private Array<ObjetoDelJuego> objetosDelJuego;

    //private Hud hud;

    public PantallaJuego(final MyGdxGame game) {
        nivelEnCurso = false;
        this.game = game;
        inicializaNivel();
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO);
        //viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport = new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO, camera);
        box2DDebugRendered = new Box2DDebugRenderer();
        Gdx.gl.glClearColor(0, 0.1f, 0f, 1);
        //todosLosNiveles = new Array<Nivel>();
        //todosLosNiveles.add(new Nivel("test.lvl")); // TODO: Inicializar y declarar los niveles del juego en una sóla línea
        //world.setContactListener(new GameContactListener());
    }

    private void inicializaNivel() {
        world = new World(new Vector2(0, -9.81f), true);
        Nivel nivel = new Nivel("001.json");

        objetosDelJuego = nivel.getObjetosDelJuego();
        for (ObjetoDelJuego objeto : objetosDelJuego){
            objeto.definirCuerpo(world);
        }
        //objetosDelJuego.add(new Spawn(world, 2, 11);
        //objetosDelJuego.add(new Meta(world, 20,23);
        Array<Jugador> jugadores = new Array<Jugador>();
        for (Jugador jugador : game.getJugadoresActivos()) {
            jugador.setObjetoJugador(new ObjetoJugador());
            jugador.getObjetoJugador().definirCuerpo(world);
            spawnPos.x = jugador.getObjetoJugador().body.getPosition().x;
            spawnPos.y = jugador.getObjetoJugador().body.getPosition().y;
        }
        nivelEnCurso = true;
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
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float dt) {
        // Actualizamos los jugadores

        for (Jugador jugador : game.getJugadoresActivos()) {
            jugador.getObjetoJugador().update(dt);
        }
        /*
        for (ObjetoDelJuego objetoDelJuego: objetosDelJuego) {
            objetoDelJuego.update(dt);
        }
        */
        // Actualizamos la simulación de box2d
        world.step(dt, 6, 2);
        camera.update();

        // Renderizamos
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        box2DDebugRendered.render(world, camera.combined);
        //game.getSpriteBatch().setProjectionMatrix(camera.combined); no deja ver sprites

        game.getSpriteBatch().begin();

        for (Jugador jugador : game.getJugadoresActivos()) {
            float ppm = 29.1f;
            Sprite sprite = new Sprite(jugador.getObjetoJugador().sprite);

            sprite.setPosition(jugador.getObjetoJugador().body.getPosition().x* ppm-245,jugador.getObjetoJugador().body.getPosition().y*ppm-290);
            sprite.scale(-0.85f);
            sprite.draw(game.getSpriteBatch());
        }

        // TODO: Componer el frame
        // Fixme: no es necesario generar una vez por frame el bitmap correspondiente al texto
        //game.getBitmapFont().draw(game.getSpriteBatch(), "> ESTA ES LA PANTALLA DE JUEGO", 100, 160);
        //hud.getStage().draw();
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
        // TODO: ¿Deberíamos recorrer aquí el array objetosDelJuego para ir llamando al método dispose() de cada objeto?
    }

    @Override
    public void conectaJugador(Jugador jugador) {
        // FIXME: En función de si la partida dentro de este nivel está en marcha deberíamos decidir si el jugador se debe añadir a la lista de jugadores activos o bien a la de jugadores en espera
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
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoIzquierda(true);
        }
    }

    @Override
    public void alLiberarIzquierda(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoIzquierda(false);
        }
    }

    @Override
    public void alPresionarDerecha(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoDerecha(true);
        }
    }

    @Override
    public void alLiberarDerecha(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoDerecha(false);
        }
    }

    @Override
    public void alPresionarBoton1(Jugador jugador) {
        // TODO Auto-generated method stub
        Gdx.app.debug("PantallaJuego", "Uno de los jugadores ha presionado su boton1");
    }

    @Override
    public void alLiberarBoton1(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alPresionarCancelar(Jugador jugador) {
        // TODO Auto-generated method stub
    }

    @Override
    public void alLiberarCancelar(Jugador jugador) {
        // TODO Auto-generated method stub
    }


}
