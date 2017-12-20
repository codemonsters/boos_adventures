package es.codemonsters.boosadventures.controlador.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import es.codemonsters.boosadventures.controlador.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 900/16*9;
		config.width = 900;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
