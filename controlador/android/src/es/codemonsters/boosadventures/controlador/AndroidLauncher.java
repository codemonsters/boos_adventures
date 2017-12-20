package es.codemonsters.boosadventures.controlador;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import es.codemonsters.boosadventures.controlador.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	private MyGdxGame game;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new MyGdxGame();
		initialize(game, config);

		if (isBluetoothAvailable()) {
			Gdx.app.log("AndroidLauncher", "BLUETOOTH ESTA ENCENDIDO");
		} else {
			Gdx.app.log("AndroidLauncher", "BLUETOOTH ESTA APAGADO");
			turnBluetoothOn();
			if (isBluetoothAvailable()) {
				Gdx.app.log("AndroidLauncher", "Ahora está encendido");
			} else {
				Gdx.app.log("AndroidLauncher", "Y sigue apagado :-(");
			}
		}
	}

	public boolean isBluetoothAvailable() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBluetoothAdapter == null)
			return false;
		else
			return true;
	}

	public void turnBluetoothOn() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBluetoothAdapter != null) {
			if(!mBluetoothAdapter.isEnabled()){
				mBluetoothAdapter.enable();
			}
		}
	}

}
