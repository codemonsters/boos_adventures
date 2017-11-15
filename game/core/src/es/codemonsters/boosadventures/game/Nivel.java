package es.codemonsters.boosadventures.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.ArrayList;

import es.codemonsters.boosadventures.game.objetosdeljuego.Bloque;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Nivel {
    JsonValue valoresJsonNivel;
    public Nivel(String archivo) {
        //TODO: Parsear el archivo JSON que recibe por argumento y a partir de él crear un array interno que contenga toda la inforamción leída
        JsonReader jsonRe = new JsonReader();
        valoresJsonNivel = jsonRe.parse(Gdx.files.internal("niveles/" + archivo));
    }

    public String getNombre() {
        String nombre = valoresJsonNivel.get("cabecera").getString("nombre");
        return nombre;
    }

    public String getSugerencia() {
        String sugerencia = valoresJsonNivel.get("cabecera").getString("sugerencia");
        return sugerencia;
    }

    public Array<ObjetoDelJuego> getObjetosDelJuego() {
        Array<ObjetoDelJuego> objetosDelJuegos = new Array<ObjetoDelJuego>();
        JsonValue objetos = valoresJsonNivel.get("objetos");
        for (JsonValue objeto: objetos) {
            String clase = objeto.getString("clase");
            if (clase.equals("bloque")) {
                Bloque bloque = new Bloque(objeto.getFloat("ancho"),objeto.getFloat("alto"),objeto.getFloat("x"),objeto.getFloat("y"),objeto.getFloat("angulo"));
                objetosDelJuegos.add(bloque);
            } else {
                Gdx.app.log("Nivel","Tipo de objeto desconocido: " + clase);
            }
        }
        return objetosDelJuegos;
    }
}