package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

import es.codemonsters.boosadventures.game.MyGdxGame;
import es.codemonsters.boosadventures.game.pantallas.PantallaJuego;

public class Palanca extends ObjetoDelJuego{
    /*

    //palanca

    */
    private float anchoBox2d, altoBox2d, xCentroBox2d, yCentroBox2d, angulo;
    private Texture textura;
    private Body bodyPalanca;
    private Body bodyBase;
    private PantallaJuego pantallaJuego;

    // Todo será más fácil si definimos todo con el mismo sistema de coordenadas (tanto lo relativo a box2d como a las texturas)
    // Por ejemplo:
    // * Que el punto (0,0) sea la esquina inferior izquierda de la pantalla
    // * Que las posiciones de los cuerpos y de las texturas se definan también respecto a su esquina inferior izquierda
    // * Que el ancho y el alto de cada objeto se defina con el que realmente tiene (y no la mitad como requiere Box2d)
    // Para eso nuestros ObjetosDelJuego deberían:
    // * Poder crearse expresando las cosas e internamente tendremos que traducir esto por ejemplo antes de usar Box2D
    // * Tener los getters y setters que sean necesarios para poder leer y escribir las posiciones y dimensiones "traducidas"

    public Palanca(float xEsquinaInfIzq, float yEsquinaInfIzq, PantallaJuego pantallaJuego) {
        super();
        // Trasladamos las coordenadas al sistema de Box2D donde los objetos tipo Box se definen respecto al centro del cuerpo
        this.pantallaJuego = pantallaJuego;
        this.anchoBox2d = 0.2f / 2;
        this.altoBox2d = 1.75f / 2;
        xCentroBox2d = xEsquinaInfIzq + anchoBox2d / 2;
        yCentroBox2d = yEsquinaInfIzq + altoBox2d / 2;
        angulo = 0;
        // Adaptamos el tamaño a Box2D, donde el ancho y el alto d
            textura = new Texture(Gdx.files.internal("Bloque/0.png"));
    }


    @Override
    public void definirCuerpo(World world) {
        //palanca
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(xCentroBox2d, yCentroBox2d);
        bodyPalanca = world.createBody(bdef);

        bodyPalanca.setUserData(this);
        PolygonShape polygonShape3 = new PolygonShape();
        final float density3 = 0.2f;
        polygonShape3.setAsBox(anchoBox2d, altoBox2d, new Vector2(0,anchoBox2d*2),angulo);
        fixture = bodyPalanca.createFixture(polygonShape3, density3);
        fixture.setFriction(0);
        fixture.setUserData(this);

        //base
        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(xCentroBox2d, yCentroBox2d);
        bodyBase = world.createBody(bdef);

        bodyBase.setUserData(this);
        polygonShape3 = new PolygonShape();
        polygonShape3.setAsBox(altoBox2d , anchoBox2d, new Vector2(0,-altoBox2d/2),angulo);
        fixture = bodyBase.createFixture(polygonShape3, density3);
        fixture.setFriction(0);
        fixture.setRestitution(0);
        fixture.setUserData(this);

        //joint de abajo
        RevoluteJointDef rj = new RevoluteJointDef();

        rj.bodyA = bodyBase;
        rj.bodyB = bodyPalanca;
        rj.enableLimit = true;
        rj.lowerAngle = -80* MathUtils.degreesToRadians;
        rj.upperAngle = 80* MathUtils.degreesToRadians;

        rj.localAnchorA.set( 0,-altoBox2d/2);
        rj.localAnchorB.set( 0,-altoBox2d/2 );

        world.createJoint(rj);
        //distance joint

        DistanceJointDef def = new DistanceJointDef();

        def.bodyA = bodyBase;
        def.bodyB = bodyPalanca;

        def.localAnchorA.set( 0, -altoBox2d/10);
        def.localAnchorB.set( 0,-altoBox2d/10 );

        def.length = 0;
        world.createJoint(def);

        FixtureDef fixtureDef = new FixtureDef();


        //sensor palanca derecha

        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(xCentroBox2d, yCentroBox2d);
        bodyBase = world.createBody(bdef);
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(altoBox2d/2, anchoBox2d), new Vector2(altoBox2d/2, -altoBox2d/2));
        fixtureDef.shape = feet;
        fixtureDef.isSensor = true;
        fixture = bodyBase.createFixture(fixtureDef);
        fixture.setUserData("sensorPalancaIzq");

        //sensor palanca izquierda
        feet.set(new Vector2(-altoBox2d/2, anchoBox2d), new Vector2(-altoBox2d/2, -altoBox2d/2));
        fixtureDef.shape = feet;
        fixtureDef.isSensor = true;
        fixture = bodyBase.createFixture(fixtureDef);
        fixture.setUserData("sensorPalancaDer");
    }

    public void giraCanones(boolean sentidoHorario) {
        pantallaJuego.giraCanones(sentidoHorario);
    }
    public void detenerGiroCanones() {
        pantallaJuego.detenerCanones();
    }

    @Override
    public void dispose() {
        // FIXME: Añadir código para eliminar este objeto
        textura.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // FIXME: Aquí deberíamos dibujar el bloque a partir de un bitmap
        //anchoBox2d,altoBox2d
        //batch.draw(textura, body.getWorldCenter().x - anchoBox2d, body.getWorldCenter().y - altoBox2d, anchoBox2d, altoBox2d, anchoBox2d * 2, altoBox2d * 2, 1, 1, angulo, 0, 0, 1, 1, false, false);
    }

}
