package es.codemonsters.boosadventures.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import es.codemonsters.boosadventures.game.ContactListenerJuego;
import es.codemonsters.boosadventures.game.Jugador;
import es.codemonsters.boosadventures.game.MyGdxGame;
import es.codemonsters.boosadventures.game.Nivel;
import es.codemonsters.boosadventures.game.Terminal;
import es.codemonsters.boosadventures.game.objetosdeljuego.Canon;
import es.codemonsters.boosadventures.game.objetosdeljuego.SensoresLimitesMundo;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;
import es.codemonsters.boosadventures.game.objetosdeljuego.Spawn;


public class PantallaJuego extends Pantalla {

    private static enum Estados {
        NACIENDO,
        JUGANDO,
        MURIENDO,
        GANANDO,
    }
    public static final double VELOCIDAD_MAXIMA_OBJETO_JUEGO = 1;
    private int numNivel = 1;
    private Estados estado = Estados.NACIENDO;
    public static final int ANCHO_DEL_MUNDO = 44;   // Ancho del mundo (en metros)
    public static final int ALTO_DEL_MUNDO = 27;    // Alto del mundo (en metros)
    private MyGdxGame game;
    //private OrthographicCamera camera;
    private Array<Nivel> todosLosNiveles;
    private boolean nivelEnCurso;
    private Terminal terminal;

    Sprite sprite;
    private World world;
    private Box2DDebugRenderer box2DDebugRendered;
    private Array<ObjetoDelJuego> objetosDelJuego;

    private boolean queremosResetearNivel = false;

    private Stage stage;
    private Stage stage2;
    private Table table;

    //private Hud hud;

    public PantallaJuego(final MyGdxGame game) {

        /*table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true);
        */

        nivelEnCurso = false;
        this.game = game;
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.setToOrtho(false, ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO);
        //viewport = new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO, camera);
        box2DDebugRendered = new Box2DDebugRenderer();
        Gdx.gl.glClearColor(0, 0.1f, 0f, 1);
        //todosLosNiveles = new Array<Nivel>();
        //todosLosNiveles.add(new Nivel("test.lvl")); // TODO: Inicializar y declarar los niveles del juego en una sóla línea
        queremosResetearNivel = true;
    }

    /**
     * Reiniciamos el nivel, comenzando de nuevo con todos los jugadores (incluyendo tanto a los activos como a los que estaban en espera)
     */
    public void resetearNivel() {
        nivelEnCurso = false;
        estado = Estados.NACIENDO;
        if (stage!=null) {
            stage.dispose();
        }
        stage = new Stage(new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO));
        //creo otro stage para poder meter el texto, si lo metemos en la anterior es demasiado grande
        stage2 = new Stage(new FitViewport(ANCHO_DEL_MUNDO*6, ALTO_DEL_MUNDO*6));

        Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        uiSkin.getFont("commodore-64").getData().setScale(0.5f);
        terminal = new Terminal(uiSkin);

        terminal.setPosition(20 ,-10);
        terminal.setSize(ANCHO_DEL_MUNDO*6, ALTO_DEL_MUNDO*6);
        terminal.setAlignment(Align.bottomLeft);

        if (world!=null) {
            world.dispose();
        }
        world = new World(new Vector2(0, -9.81f), false);

        Nivel nivel = new Nivel("00"+numNivel+".json",this);

        objetosDelJuego = nivel.getObjetosDelJuego();

         Vector2 spawnPos = new Vector2();

        stage2.addActor(terminal);
        terminal.agregarLinea(nivel.getNombre());
        terminal.agregarLinea(nivel.getSugerencia());

        for (ObjetoDelJuego objeto : objetosDelJuego) {
            objeto.definirCuerpo(world);
            if (objeto instanceof Spawn) {
                spawnPos = ((Spawn)objeto).getPosicion();
            }
            stage.addActor(objeto);
        }


        // Añadimos sensores fuera de los límites del mundo
        SensoresLimitesMundo sensoresLimitesMundo = new SensoresLimitesMundo();
        sensoresLimitesMundo.definirCuerpo(world);

        for (Jugador jugador : game.getJugadores()) {
            jugador.setObjetoJugador(new ObjetoJugador(spawnPos.x, spawnPos.y, this, jugador));

            jugador.getObjetoJugador().definirCuerpo(world);
            //spawnPos.x = jugador.getObjetoJugador().getBody().getPosition().x;
            //spawnPos.y = jugador.getObjetoJugador().getBody().getPosition().y;
            jugador.getObjetoJugador().getBody().setAngularVelocity(0);
            jugador.getObjetoJugador().getBody().setLinearVelocity(new Vector2(0,0));
            jugador.getObjetoJugador().getBody().applyForceToCenter(new Vector2(MathUtils.random(500f,-500f), MathUtils.random(1000f,500f)), true);
            stage.addActor(jugador.getObjetoJugador());
        }
        world.setContactListener(new ContactListenerJuego(this));

        nivelEnCurso = true;
    }

    public void setSpawnPosition(Vector2 posicion) {

    }

    public void siguienteNivel(ObjetoJugador objetoJugador){
        numNivel++;
        Gdx.app.debug("PantallaJuego","Cargando nivel "+numNivel);
        resetearNivel();
    }

    public  void haMuerto(ObjetoJugador objetoJugador) {
        for (Jugador jugador : game.getJugadores()){
            if (jugador.getObjetoJugador() == objetoJugador){
                Gdx.app.log("PantallaJuego","Jugador " + jugador.getNombre() + " ha muerto");
                queremosResetearNivel = true;
                return;
            }
        }
        Gdx.app.error("PantallaJuego", "Esto no debería haber pasado: no hemos encontrado al jugador que acaba de morir");
        queremosResetearNivel = true;
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
    public void giraCanones(boolean sentidoHorario) {
        for (ObjetoDelJuego oj : objetosDelJuego) {
            if (oj instanceof Canon){
                ((Canon) oj).gira(sentidoHorario);
            }
        }
    }
    public void detenerCanones() {
        for (ObjetoDelJuego oj : objetosDelJuego) {
            if (oj instanceof Canon){
                ((Canon) oj).detenGiro();
            }
        }
    }

    public void dispararCanones() {
        for (ObjetoDelJuego oj : objetosDelJuego) {
            if (oj instanceof Canon){
                ((Canon) oj).disparar();
            }
        }
    }
    @Override
    public void render(float dt) {
        if (queremosResetearNivel) {
            resetearNivel();
            queremosResetearNivel = false;
        } else {
            // Actualizamos la simulación de box2d
            world.step(dt, 6, 2);

            // Renderizamos
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            box2DDebugRendered.render(world, stage.getCamera().combined);
            //game.getSpriteBatch().setProjectionMatrix(camera.combined);

            /*
            game.getSpriteBatch().begin();

            for (Jugador jugador : game.getJugadores()) {
                Sprite sprite = new Sprite(jugador.getObjetoJugador().sprite);

                //float xSprite = (jugador.getObjetoJugador().body.getPosition().x - 0.75f ) * game.getPpm();
                //float ySprite = (jugador.getObjetoJugador().body.getPosition().y - 0.75f) * game.getPpm();
                float xSprite = jugador.getObjetoJugador().body.getPosition().x*game.getPpm() - 50;
                float ySprite = -25;
                sprite.setPosition(xSprite, ySprite);
                sprite.scale(-0.85f);
                sprite.draw(game.getSpriteBatch());
            }
            game.getSpriteBatch().end();
            */
            // TODO: Componer el frame
            // Fixme: no es necesario generar una vez por frame el bitmap correspondiente al texto

            stage.act(dt);
            stage.draw();
            stage2.act(dt);
            stage2.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (stage!=null){
            stage.getViewport().update(width, height, true);
        }
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
        stage.dispose();
    }

    @Override
    public void conectaJugador(Jugador jugador) {
        // FIXME: En función de si la partida dentro de este nivel está en marcha deberíamos decidir si el jugador se debe añadir a la lista de jugadores activos o bien a la de jugadores en espera
        game.agregaJugador(jugador);
    }

    @Override
    public void alPresionarArriba(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoArriba(true);
        }
    }

    @Override
    public void alLiberarArriba(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoArriba(false);
        }
    }

    @Override
    public void alPresionarAbajo(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoAbajo(true);
        }
    }

    @Override
    public void alLiberarAbajo(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoAbajo(false);
        }
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
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoBoton1(true);
        }
    }

    @Override
    public void alLiberarBoton1(Jugador jugador) {
        if (jugador.getObjetoJugador() != null) {
            jugador.getObjetoJugador().setPresionandoBoton1(false);
        }
    }

    @Override
    public void alPresionarCancelar(Jugador jugador) {
        // El jugador quiere abandonar la partida
        game.eliminaJugador(jugador);
    }

    @Override
    public void alLiberarCancelar(Jugador jugador) {
        // Ignoramos el evento porque ya hemos atendido esto en el método alPresionarCancelar
    }

}
