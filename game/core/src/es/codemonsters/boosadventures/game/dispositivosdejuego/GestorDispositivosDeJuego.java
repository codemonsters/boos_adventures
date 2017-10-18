package es.codemonsters.boosadventures.game.dispositivosdejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class GestorDispositivosDeJuego {
    private Array<DispositivoDeJuego> dispositivosDeJuegoConectados = new Array<DispositivoDeJuego>();

    /**
     * Conecta un nuevo dispositivo a la lista de dispositivos de juego y le asigna un id
     * @param dispositivoDeJuego    El dispositivo a conectar
     * @return  El id asignado al dispositivo que acaba de ser conectado
     */
    public int conectar(DispositivoDeJuego dispositivoDeJuego) {
        // Buscamos el primer id libre (el número positivo más bajo no ocupado)
        int idNuevoDispositivo = 0;
        boolean libre = true;  // Variable flag, inicialmente consideramos que el id no está asignado a otro dispositivo
        while (true) {
            for (DispositivoDeJuego dispositivoExistente : dispositivosDeJuegoConectados) {
                if (dispositivoExistente.getId() == idNuevoDispositivo) {
                    libre = false;
                    break;  // Ya existe otro dispositivo con este id
                }
            }
            if (libre == false) {
                idNuevoDispositivo++;   // El id anteriormente comprobado estaba ocupado, probamos con el siguiente
                libre = true;
            } else {
                break;
            }
        }
        dispositivoDeJuego.setId(idNuevoDispositivo);   // Asignamos el id al dispositivo recientemente conectado
        dispositivosDeJuegoConectados.add(dispositivoDeJuego);     // Y lo añadimos a la lista de dispositivos activos
        Gdx.app.debug("GestorDispositivosDeJuego", "Nuevo dispositivo de juego conectado (id = " + dispositivoDeJuego.getId() + ")");
        return dispositivoDeJuego.getId();
    }

    /**
     * Ejecuta el método desconectar() en cada dispositivo conectado y lo elimina de la lista dispositivosDeJuegoConectados
     */
    public void desconectarTodos() {
        for (DispositivoDeJuego dispositivo: dispositivosDeJuegoConectados) {
            dispositivo.desconectar();
            dispositivosDeJuegoConectados.removeValue(dispositivo, false);
        }

    }
}
