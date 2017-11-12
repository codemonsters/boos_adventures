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

    private String archivo;
    private  World world;
    public Nivel(String archivo, World world) {
        //TODO: Parsear el archivo JSON que recibe por argumento y a partir de él crear un array interno que contenga toda la inforamción leída
        this.archivo = archivo;
        this.world = world;
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

        Array<ObjetoDelJuego> objetos = new Array<ObjetoDelJuego>();
        Json json = new Json();
        JsonReader jsonR = new JsonReader();
        ArrayList<DataBlock> list = json.fromJson(ArrayList.class,DataBlock.class,Gdx.files.internal("niveles/"+archivo));
        for (DataBlock v : list) {
            if (v.clase.equals("Bloque")){
                objetos.add(new Bloque(world,v.ancho,v.alto,v.x,v.y,v.angulo));
            }
        }


        /*JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal("niveles/001.json"));
        System.out.println(base);
        JsonValue component = base.get("objetos");
        System.out.println(component.getString("clase"));
        for (JsonValue componente : base.get("objetos"))
        {
            System.out.println(component.getString("clase"));
        }*/
        return objetos;
    }

}
class DataBlock {
    public String nombre;
    public String sugerencia;
    public String clase;
    public int x;
    public int y;
    public int ancho;
    public int alto;
    public int angulo;
    public int regiontextura;
}