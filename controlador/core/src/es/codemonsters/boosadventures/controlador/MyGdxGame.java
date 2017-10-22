package es.codemonsters.boosadventures.controlador;

import com.badlogic.gdx.Screen;

public class MyGdxGame extends com.badlogic.gdx.Game {
	public Screen pantallaActiva;

	public MyGdxGame() {}

	public void create() { setPantalla(new Menu(this)); }

	public void setPantalla(Screen pantalla) {
		System.out.println(pantalla);
		Screen pantallaAnterior = pantallaActiva;
		pantallaActiva = pantalla;
		setScreen(pantallaActiva);
	}
}
