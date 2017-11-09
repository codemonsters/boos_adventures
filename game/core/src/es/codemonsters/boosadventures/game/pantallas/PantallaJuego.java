package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    private World world;
    private Box2DDebugRenderer box2DDebugRendered;
    private LimitesNivel limitesNivel;
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
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        //todosLosNiveles = new Array<Nivel>();
        //todosLosNiveles.add(new Nivel("test.lvl")); // TODO: Inicializar y declarar los niveles del juego en una sóla línea
        //world.setContactListener(new GameContactListener());
    }

    private void inicializaNivel() {
        world = new World(new Vector2(0, -9.81f), true);
        objetosDelJuego = new Array<ObjetoDelJuego>();
        objetosDelJuego.add(new Bloque(world, ANCHO_DEL_MUNDO,1,0,0));
        objetosDelJuego.add(new Bloque(world, 1,1,0,1));
        //objetosDelJuego.add(new Spawn(world, 2, 11);
        //objetosDelJuego.add(new Meta(world, 20,23);
        Array<Jugador> jugadores = new Array<Jugador>();
        for (Jugador jugador : game.getJugadoresActivos()) {
            jugador.setObjetoJugador(new ObjetoJugador(world));
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
        game.getSpriteBatch().setProjectionMatrix(camera.combined); // Inidicamos al SpriteBatch a dónde está mirando la cámara para sólo renderizar lo que la cámara vé
        game.getSpriteBatch().begin();
        // TODO: Componer el frame
        // Fixme: no es necesario generar una vez por frame el bitmap correspondiente al texto
        game.getBitmapFont().draw(game.getSpriteBatch(), "> ESTA ES LA PANTALLA DE JUEGO", 100, 160);
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
        Gdx.app.debug("PantallaJuego", "Uno de los jugadores ha presionado su boton1");
    }

    @Override
    public void alLiberarBoton1(Jugador jugador) {
        // TODO Auto-generated method stub
    }


}
