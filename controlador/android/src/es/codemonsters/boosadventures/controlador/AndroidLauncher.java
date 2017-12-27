package es.codemonsters.boosadventures.controlador;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.util.Log;

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
			if (isBluetoothOn()) {
				Gdx.app.debug("AndroidLauncher", "Bluetooth ya estaba activado");
			} else {
				turnBluetoothOn();
				if (isBluetoothOn()) {
					Gdx.app.debug("AndroidLauncher", "Bluetooth activado correctamente");
				} else {
					Gdx.app.error("AndroidLauncher", "Error al intentar activar bluetooth");
				}
			}
		} else {
			// El dispositivo no soporta bluetooth
			Gdx.app.error("AndroidLauncher", "El dispositivo no soporta bluetooth");
		}
	}

	public boolean isBluetoothAvailable() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBluetoothAdapter == null)
			return false;
		else
			return true;
	}

	public boolean isBluetoothOn() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBluetoothAdapter != null) {
			if(!mBluetoothAdapter.isEnabled()){
				return false;
			}
		}
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
