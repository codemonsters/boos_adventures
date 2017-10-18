package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;

import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoDeJuego;
import es.codemonsters.boosadventures.game.dispositivosdejuego.DispositivoTeclado;
import es.codemonsters.boosadventures.game.dispositivosdejuego.GestorDispositivosDeJuego;
import es.codemonsters.boosadventures.game.screens.MenuScreen;

public class MyGdxGame extends Game {
	public static final String nombreDelJuego = "Boo's Adventures";
	public static final String versionDelJuego = "0.1a";

	private SpriteBatch batch;
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontParameter fontGeneratorDefaultParameters;
	private BitmapFont bitmapFont;
    private GestorDispositivosDeJuego gestorDispositivosDeJuego = new GestorDispositivosDeJuego();

	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

	public GestorDispositivosDeJuego getGestorDispositivosDeJuego() { return gestorDispositivosDeJuego; }

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

		// Creamos dos dispositivos de juego teclado para ponder utilizar el juego al menos durante el desarrollo
		DispositivoDeJuego tecladoJugador1 = new DispositivoTeclado(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.ENTER, Input.Keys.BACKSPACE);
		tecladoJugador1.setJugador(new Jugador("Jugador 1"));
		getGestorDispositivosDeJuego().conectar(tecladoJugador1);
		DispositivoDeJuego tecladoJugador2 = new DispositivoTeclado(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.SPACE, Input.Keys.ESCAPE);
		tecladoJugador2.setJugador(new Jugador("Jugador 2"));
		getGestorDispositivosDeJuego().conectar(tecladoJugador2);

		// Definimos la pantalla en la que iniciará el juego
		setScreen(new MenuScreen(this));
	}

	/*
	@Override
	public void render() {
		super.render();
	}
	*/

	@Override
	public void dispose () {
        gestorDispositivosDeJuego.desconectarTodos();   // TODO: Deberíamos desconectar los dispositivos incluso cuando cerramos la aplicación con el aspa o bruscamente
		screen.dispose();
		bitmapFont.dispose();
		fontGenerator.dispose();
		batch.dispose();
	}
}
