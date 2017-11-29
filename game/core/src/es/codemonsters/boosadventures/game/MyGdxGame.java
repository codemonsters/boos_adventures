package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;

import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoDeJuego;
import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoTeclado;
import es.codemonsters.boosadventures.game.dispositivosdejuego.GestorDispositivosDeJuego;
import es.codemonsters.boosadventures.game.pantallas.Pantalla;
import es.codemonsters.boosadventures.game.pantallas.PantallaMenu;

public class MyGdxGame extends Game {
	public static final String nombreDelJuego = "Boo's Adventures";
	public static final String versionDelJuego = "0.1a";

	private SpriteBatch batch;
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontParameter fontGeneratorDefaultParameters;
	private BitmapFont bitmapFont;
    private GestorDispositivosDeJuego gestorDispositivosDeJuego = new GestorDispositivosDeJuego();
    private InputMultiplexer inputMultiplexer;  // La lista de InputProcessors que utilizaremos
	private InputProcessorJugadores inputProcessorJugadores;
	private Pantalla pantallaActiva;
    private Array<Jugador> jugadoresEnEspera;   // Lista de jugadores conectados que quieren incorporarse a la partida
    private Array<Jugador> jugadoresActivos;    // Lista de jugadores conectados que están participando en la partida actual

	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

	protected GestorDispositivosDeJuego getGestorDispositivosDeJuego() { return gestorDispositivosDeJuego; }

    public InputMultiplexer getInputMultiplexer() { return inputMultiplexer; }

	public void setPantalla(Pantalla pantalla) {
        Pantalla pantallaAnterior = getPantalla();
		this.pantallaActiva = pantalla;
		setScreen(getPantalla());
		inputProcessorJugadores = getPantalla();
        if (pantallaAnterior != null) {
            pantallaAnterior.dispose();
        }
	}

    public void agregaJugadorActivo(Jugador jugador) {
        synchronized (jugadoresActivos) {
            if (jugadoresActivos.contains(jugador, false)) {
                Gdx.app.debug("MyGdxGame", "'" + jugador + "' ya estaba en la lista de jugadores activos, ignorando esta nueva solicitud");
            } else {
                jugadoresActivos.add(jugador);
                Gdx.app.debug("MyGdxGame", "'" + jugador + "' añadido a la lista de jugadores activos");
            }
        }
    }

    public void agregaJugadorEnEspera(Jugador jugador) {
        synchronized (jugadoresEnEspera) {
            if (jugadoresEnEspera.contains(jugador, false)) {
                Gdx.app.debug("MyGdxGame", "El jugador ya estaba en la lista de jugadores en espera, ignorando esta nueva solicitud");
            } else {
                jugadoresEnEspera.add(jugador);
                Gdx.app.debug("MyGdxGame", "Nuevo jugador añadido a la lista de jugadores en espera (jugador: " + jugador + ")");
            }
        }
    }

    public void eliminaJugador(Jugador jugador) {
		synchronized (jugadoresActivos){
			synchronized (jugadoresEnEspera) {
				if (jugadoresActivos.contains(jugador, false)) {
					// Debemos eliminar un jugador que está jugando la partida actual
					jugador.dispose();
					jugadoresActivos.removeValue(jugador, false);
				} else if (jugadoresEnEspera.contains(jugador, false)) {
					// Debemos eliminar un jugador que está en espera
					jugador.dispose();
					jugadoresEnEspera.removeValue(jugador, false);
				} else {
					// ¡No hemos encontrado el jugador que se quiere eliminar!
					Gdx.app.error("MyGdxGame", "Esto no debería suceder nunca: se ha intentado eliminar un jugador que no se ha encontrado ni en la lista jugadoresActivos ni en jugadoresEnEspera");
				}
			}
		}
	}

    public void incorporaJugadoresEnEspera() {
		synchronized(jugadoresEnEspera) {
			for (int i = 0; i < jugadoresEnEspera.size; i++) {
				Jugador jugadorEnEspera = jugadoresEnEspera.get(i);
				synchronized(jugadoresActivos) {
					jugadoresActivos.add(jugadorEnEspera);
				}
				jugadoresEnEspera.removeIndex(i);
			}
		}
	}

	public Array<Jugador> getJugadoresActivos() {
		return jugadoresActivos;
	}

	public Pantalla getPantalla() { return pantallaActiva; }

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		batch = new SpriteBatch();	// Creamos un único SpriteBatch para todo el juego

		// Configuramos la fuente por defecto
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("monofonto.ttf"));
		fontGeneratorDefaultParameters = new FreeTypeFontParameter();
		fontGeneratorDefaultParameters.size = 16;
		//fontGeneratorDefaultParameters.color = Color.GREEN;
		bitmapFont = fontGenerator.generateFont(fontGeneratorDefaultParameters);
		bitmapFont.setColor(Color.GREEN);

        inputMultiplexer = new InputMultiplexer();  // Contenedor donde colocaremos todos los InputProcessor que necesitemos
		Gdx.input.setInputProcessor(inputMultiplexer);

        // Creamos las listas de jugadores activos y jugadores en espera
        jugadoresActivos = new Array<Jugador>();
        jugadoresEnEspera = new Array<Jugador>();

		// Creamos dos dispositivos de juego teclado para ponder utilizar el juego al menos durante el desarrollo
		DispositivoDeJuego tecladoJugador1 = new DispositivoTeclado(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.CONTROL_RIGHT, Input.Keys.BACKSPACE, new Jugador("Jugador 1"), this);
		getGestorDispositivosDeJuego().conectar(tecladoJugador1);
		DispositivoDeJuego tecladoJugador2 = new DispositivoTeclado(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.SPACE, Input.Keys.SPACE, new Jugador("Jugador 2"), this);
		//tecladoJugador2.setJugador();
		getGestorDispositivosDeJuego().conectar(tecladoJugador2);

		// Definimos la pantalla en la que iniciará el juego
		setPantalla(new PantallaMenu(this));
	}

	@Override
	public void dispose () {
        gestorDispositivosDeJuego.desconectarTodos();   // TODO: Deberíamos desconectar los dispositivos incluso cuando cerramos la aplicación con el aspa o bruscamente
        getPantalla().dispose();
		bitmapFont.dispose();
		fontGenerator.dispose();
		batch.dispose();
	}
}
