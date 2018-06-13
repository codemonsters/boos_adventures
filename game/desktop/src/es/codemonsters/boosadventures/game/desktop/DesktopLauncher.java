package es.codemonsters.boosadventures.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import es.codemonsters.boosadventures.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.title = MyGdxGame.nombreDelJuego;
		//config.fullscreen = true;
		new LwjglApplication(new MyGdxGame(new ServicioBluetoothDesktop()), config);
	}
}
