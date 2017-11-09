package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Bloque extends ObjetoEstatico {

    private World world;
    private float anchoBox2d, altoBox2d, xBox2d, yBox2d;
    // Todo será más fácil si en los objetos del juego definimos todo con el mismo sistema de coordenadas (es decir tanto lo relativo a box2d como a las texturas, etc).
    // Por ejemplo:
    // * Que el punto (0,0) sea la esquina inferior izquierda de la pantalla
    // * Que las posiciones de los cuerpos y de las texturas se definan también respecto a su esquina inferior izquierda
    // * Que el ancho y el alto de cada objeto se defina con el que realmente tiene (y no la mitad como requiere Box2d)
    // Para eso nuestros ObjetosDelJuego deberían:
    // * Poder crearse expresando las cosas e internamente tendremos que traducir esto por ejemplo antes de usar Box2D
    // * Tener los getters y setters que sean necesarios para poder leer y escribir las posiciones y dimensiones "traducidas"

    public Bloque(World world, float ancho, float alto, float x, float y) {
        super(world);
        this.world = world;
        // Trasladamos las coordenadas al sistema de Box2D donde se definen respecto al centro del cuerpo
        this.xBox2d = x + ancho/2;
        this.yBox2d = y + alto/2;
        // Adaptamos el tamaño a Box2D, donde el ancho y el alto de los rectángulos se define como la mitad del que realmente tienen
        this.anchoBox2d = ancho/2;
        this.altoBox2d = alto/2;
        definirCuerpo();
    }

    private void definirCuerpo() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(xBox2d, yBox2d);
        Body body = world.createBody(bdef);
        body.setUserData(this);

        PolygonShape polygonShape = new PolygonShape();
        final float floor_density = 10; // TODO: Revisar si esta es la densidad que queremos para este tipo de objeto

        polygonShape.setAsBox(anchoBox2d, altoBox2d);
        fixture = body.createFixture(polygonShape, floor_density);
        fixture.setUserData(this);
    }


}
