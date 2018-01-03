package es.codemonsters.boosadventures.controlador;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;

import es.codemonsters.boosadventures.controlador.pantallas.Conexion;
import es.codemonsters.boosadventures.controlador.pantallas.Menu;

public class MyGdxGame extends Game {

	public Screen pantallaActiva;

	public MyGdxGame() {}

	public void create() {
		//setPantalla(new Menu(this));
		setPantalla(new Conexion(this));
	}

	public void setPantalla(Screen pantalla) {
		System.out.println(pantalla);
		Screen pantallaAnterior = pantallaActiva;
		pantallaActiva = pantalla;
		setScreen(pantallaActiva);
	}
}
