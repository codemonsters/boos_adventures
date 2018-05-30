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
import es.codemonsters.boosadventures.game.objetosdeljuego.Canon;
import es.codemonsters.boosadventures.game.objetosdeljuego.Cuerda;
import es.codemonsters.boosadventures.game.objetosdeljuego.Meta;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import es.codemonsters.boosadventures.game.objetosdeljuego.Palanca;
import es.codemonsters.boosadventures.game.objetosdeljuego.Piedra;
import es.codemonsters.boosadventures.game.objetosdeljuego.Rebotador;
import es.codemonsters.boosadventures.game.objetosdeljuego.Spawn;
import es.codemonsters.boosadventures.game.objetosdeljuego.Trampolin;
import es.codemonsters.boosadventures.game.pantallas.PantallaJuego;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Nivel {
    private PantallaJuego pantallaJuego;
    private JsonValue valoresJsonNivel;
    private String archivo;
    public Nivel(String archivo, PantallaJuego pantallaJuego) {
        this.pantallaJuego = pantallaJuego;
        this.archivo = archivo;
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
                Bloque bloque = new Bloque(objeto.getFloat("ancho"),objeto.getFloat("alto"),objeto.getFloat("x"),objeto.getFloat("y"),objeto.getFloat("angulo"),objeto.getBoolean("instakill"));
                objetosDelJuegos.add(bloque);
            } else if (clase.equals("rebotador")) {
                Rebotador rebotador = new Rebotador(objeto.getFloat("ancho"),objeto.getFloat("alto"),objeto.getFloat("x"),objeto.getFloat("y"),objeto.getFloat("angulo"));
                objetosDelJuegos.add(rebotador);
            }  else if (clase.equals("palanca")) {
                Palanca palanca = new Palanca(objeto.getFloat("x"),objeto.getFloat("y"),pantallaJuego);
                objetosDelJuegos.add(palanca);
            } else if (clase.equals("canon")) {
                Canon canon = new Canon(objeto.getFloat("x"),objeto.getFloat("y"),objeto.getFloat("angulo"));
                objetosDelJuegos.add(canon);
            } else if (clase.equals("trampolin")) {
                Trampolin trampolin = new Trampolin(objeto.getFloat("x"),objeto.getFloat("y"),objeto.getFloat("angulo"));
                objetosDelJuegos.add(trampolin);
            } else if (clase.equals("cuerda")) {
                Cuerda cuerda = new Cuerda(objeto.getFloat("longitud"),objeto.getFloat("x"),objeto.getFloat("y"));
                objetosDelJuegos.add(cuerda);
            } else if (clase.equals("meta")) {
                Meta meta = new Meta(objeto.getFloat("x"),objeto.getFloat("y"));
                objetosDelJuegos.add(meta);
            } else if (clase.equals("piedra")) {
                Piedra piedra = new Piedra(objeto.getFloat("radio"),objeto.getFloat("x"),objeto.getFloat("y"));
                objetosDelJuegos.add(piedra);
            } else if (clase.equals("spawn")) {
                Spawn spawn = new Spawn(objeto.getFloat("x"),objeto.getFloat("y"));
                objetosDelJuegos.add(spawn);
            } else {
                Gdx.app.log("Nivel","Tipo de objeto desconocido encontrado en " + archivo + ": " + clase);
            }
        }
        return objetosDelJuegos;
    }
}