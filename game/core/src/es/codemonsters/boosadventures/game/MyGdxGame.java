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

import es.codemonsters.boosadventures.game.ConexionBluetooth.ServicioBluetooth;
import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoDeJuego;
import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoTeclado;
import es.codemonsters.boosadventures.game.dispositivosdejuego.GestorDispositivosDeJuego;
import es.codemonsters.boosadventures.game.pantallas.Pantalla;
import es.codemonsters.boosadventures.game.pantallas.PantallaMenu;

public class MyGdxGame extends Game {
	public static final String nombreDelJuego = "Boo's Adventures";
	public static final String versionDelJuego = "0.1a";

	private ServicioBluetooth ib;
	private SpriteBatch batch;
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontParameter fontGeneratorDefaultParameters;
	private BitmapFont bitmapFont;
    private GestorDispositivosDeJuego gestorDispositivosDeJuego = new GestorDispositivosDeJuego();
    private InputMultiplexer inputMultiplexer;  // La lista de InputProcessors que utilizaremos
	private InputProcessorJugadores inputProcessorJugadores;
	private Pantalla pantallaActiva;
    private Array<Jugador> jugadores;    // Lista de jugadores conectados

	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

	protected GestorDispositivosDeJuego getGestorDispositivosDeJuego() { return gestorDispositivosDeJuego; }

	private float ppm = 0;
    public InputMultiplexer getInputMultiplexer() { return inputMultiplexer; }

    public MyGdxGame(ServicioBluetooth ib){
    	this.ib = ib;
    	ib.iniciarServicio();
	}

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
        synchronized (jugadores) {
            if (jugadores.contains(jugador, false)) {
                Gdx.app.debug("MyGdxGame", "'" + jugador + "' ya estaba en la lista de jugadores activos, ignorando esta nueva solicitud");
            } else {
                Gdx.app.debug("MyGdxGame", "'" + jugador + "' añadido a la lista de jugadores activos");
            }
        }
    }

    public void agregaJugador(Jugador jugador) {
        synchronized (jugadores) {
            if (jugadores.contains(jugador, false)) {
                Gdx.app.debug("MyGdxGame", "El jugador ya estaba en la lista de jugadores, ignorando esta nueva solicitud");
            } else {
				jugadores.add(jugador);
                Gdx.app.debug("MyGdxGame", "Nuevo jugador añadido a la lista de jugadores (jugador: " + jugador + ")");
            }
        }
    }

    public void eliminaJugador(Jugador jugador) {
		synchronized (jugadores) {
			if (jugadores.contains(jugador, false)) {
				// Debemos eliminar un jugador que está jugando la partida actual
				Gdx.app.log("MyGdxGame", "Jugador + " + jugador + " eliminado de la lista de jugadores");
				jugadores.removeValue(jugador, false);
				jugador.dispose();
			} else {
				// ¡No hemos encontrado el jugador que se quiere eliminar!
				Gdx.app.error("MyGdxGame", "Esto no debería suceder nunca: se ha intentado eliminar un jugador que no se ha encontrado");
			}
		}
	}

	public Array<Jugador> getJugadores() {
		return jugadores;
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
        jugadores = new Array<Jugador>();

		// Creamos dos dispositivos de juego teclado para ponder utilizar el juego al menos durante el desarrollo
		DispositivoDeJuego tecladoJugador1 = new DispositivoTeclado(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.SPACE, Input.Keys.SPACE, new Jugador("Jugador 2"), this);
		getGestorDispositivosDeJuego().conectar(tecladoJugador1);
		DispositivoDeJuego tecladoJugador2 = new DispositivoTeclado(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.CONTROL_RIGHT, Input.Keys.BACKSPACE, new Jugador("Jugador 1"), this);
		getGestorDispositivosDeJuego().conectar(tecladoJugador2);

		// Definimos la pantalla en la que iniciará el juego
		setPantalla(new PantallaMenu(this));
	}

	public float getPpm(){
		return  ppm;
	}

	@Override
	public void dispose() {
        gestorDispositivosDeJuego.desconectarTodos();   // TODO: Deberíamos desconectar los dispositivos incluso cuando cerramos la aplicación con el aspa o bruscamente
        getPantalla().dispose();
		bitmapFont.dispose();
		fontGenerator.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		if (Gdx.graphics.getWidth() / Gdx.graphics.getHeight() > 16 / 9 ) {
			// La pantalla es muy ancha, calculamos los píxeles por metro a partir del alto (suponemos que hay barras en los lados)
			ppm = Gdx.graphics.getHeight() / 27;
		}
		else{
			//la pantalla es muy alta o exactamente 16 / 9 calculamos los puntos por pulgada basandonos en el ancho
			ppm = Gdx.graphics.getWidth() / 48;
		}
		Gdx.app.debug("MyGdxGame", "Ventana redimensionada. ppm = " + ppm);
		getPantalla().resize(width, height);

	}
}
