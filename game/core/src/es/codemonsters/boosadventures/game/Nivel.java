package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.utils.Array;

import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Nivel {

    private String archivo;

    public Nivel(String archivo) {
        //TODO: Parsear el archivo JSON que recibe por argumento y a partir de él crear un array interno que contenga toda la inforamción leída
        this.archivo = archivo;
    }

    public String getNombre() {
        // TODO: Implementar
        throw new NotImplementedException();
    }

    public String getSugerencia() {
        // TODO: Implementar
        throw new NotImplementedException();
    }

    public Array<ObjetoDelJuego> getObjetosDelJuego() {
        //TODO: Devolver todos los ObjetoDelJuego encontrados
        throw new NotImplementedException();
    }

}
