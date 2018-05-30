package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Cuerda extends ObjetoDelJuego {
    private static final float densidad = 1;
    private float longitud, xCentroSoporte, yCentroSoporte;
    private static final  float altoTramo = 0.25f;
    private static final  float anchoTramo = 0.1f;
    private Texture textura;
    private Body body;

    // Todo será más fácil si definimos todo con el mismo sistema de coordenadas (tanto lo relativo a box2d como a las texturas)
    // Por ejemplo:
    // * Que el punto (0,0) sea la esquina inferior izquierda de la pantalla
    // * Que las posiciones de los cuerpos y de las texturas se definan también respecto a su esquina inferior izquierda
    // * Que el ancho y el alto de cada objeto se defina con el que realmente tiene (y no la mitad como requiere Box2d)
    // Para eso nuestros ObjetosDelJuego deberían:
    // * Poder crearse expresando las cosas e internamente tendremos que traducir esto por ejemplo antes de usar Box2D
    // * Tener los getters y setters que sean necesarios para poder leer y escribir las posiciones y dimensiones "traducidas"

    public Cuerda(float longitud, float xSoporte, float ySoporte) {
        super();
        // Trasladamos las coordenadas al sistema de Box2D donde los objetos tipo Box se definen respecto al centro del cuerpo
        xCentroSoporte = xSoporte;
        yCentroSoporte = ySoporte;
        this.longitud = longitud;
        // Adaptamos el tamaño a Box2D, donde el ancho y el alto de los rectángulos se define como la mitad del que realmente tienen
        //TODO: Los bloques deberían poder compartir textura
            textura = new Texture(Gdx.files.internal("Bloque/0.png"));
    }



    @Override
    public void definirCuerpo(World world) {
        BodyDef bdef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        // Creamos el soporte estático
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(xCentroSoporte, yCentroSoporte);
        body = world.createBody(bdef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(altoTramo);
        //fixtureDef.restitution = 0.1f;
        fixtureDef.restitution = 0;
        fixtureDef.density = densidad;
        fixtureDef.friction = 0.5f;
        fixtureDef.isSensor = true;
        fixtureDef.shape = circleShape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        //body.setTransform(body.getWorldCenter(), Utiles.gradosSexagesimalesARadianes(angulo));
        body.setUserData(this);

        // Creamos los tramos de la cuerda
        Body prevBody = body;
        fixtureDef.isSensor = false;
        bdef.type = BodyDef.BodyType.DynamicBody;
        int numTramos = (int)Math.ceil(longitud/altoTramo);
        for(int i=0; i< numTramos; i++)
        {
            // El cuerpo de cada tramo
            bdef.position.set(xCentroSoporte, yCentroSoporte-(altoTramo*2)*i);
            Body newBody = world.createBody(bdef);

            PolygonShape polygonShape = new PolygonShape();
            Vector2 anchorJoint;
            if (i == numTramos - 1) {
                // Estamos tratando el último tramo
                float altoUltimo = longitud -altoTramo*i;
                polygonShape.setAsBox(anchoTramo, altoUltimo, new Vector2(0f, 0f), 0);
                anchorJoint = new Vector2(xCentroSoporte, (yCentroSoporte-(altoUltimo*2)*i)+altoUltimo/2);
            } else {
                polygonShape.setAsBox(anchoTramo, altoTramo, new Vector2(0f, 0f), 0);
                anchorJoint = new Vector2(xCentroSoporte, (yCentroSoporte-(altoTramo*2)*i)+altoTramo/2);
            }

            fixtureDef.shape = polygonShape;
            //fixtureDef.isSensor = true;
            fixtureDef.density = densidad;
            fixture = newBody.createFixture(fixtureDef);
            fixture.setUserData(this);

            // El joint entre este tramo y el anterior

            RevoluteJointDef jd = new RevoluteJointDef();
            jd.initialize(prevBody, newBody, anchorJoint);
            world.createJoint(jd);
            prevBody = newBody;
        }

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
        //batch.draw(textura, body.getWorldCenter().x - anchoTramo, body.getWorldCenter().y - altoTramo, anchoTramo, altoTramo, anchoTramo * 2, altoTramo * 2, 1, 1, 0, 0, 0, 1, 1, false, false);
    }
}