package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Canon extends ObjetoDelJuego{
    private float altoPaloBox2d = 2;
    private float velocidadGiro = 0;
    private float anchoPaloBox2d = 0.1f;
    private float anchoInterior = 2f;
    private float xCentroBox2d, yCentroBox2d, angulo;
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

    public Canon(float xEsquinaInfIzq, float yEsquinaInfIzq, float angulo) {
        super();
        // Trasladamos las coordenadas al sistema de Box2D donde los objetos tipo Box se definen respecto al centro del cuerpo
        xCentroBox2d = xEsquinaInfIzq + anchoPaloBox2d / 2;
        yCentroBox2d = yEsquinaInfIzq + anchoPaloBox2d / 2;
        // Adaptamos el tamaño a Box2D, donde el ancho y el alto de los rectángulos se define como la mitad del que realmente tienen
        this.angulo = angulo;
        //TODO: Los bloques deberían poder compartir textura
        textura = new Texture(Gdx.files.internal("Bloque/0.png"));
    }


    @Override
    public void definirCuerpo(World world) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.position.set(xCentroBox2d, yCentroBox2d);
        body = world.createBody(bdef);
        body.setUserData(this);

        //pared derecha
        PolygonShape polygonShape = new PolygonShape();
        final float density = 0.2f;
        polygonShape.setAsBox(anchoPaloBox2d, altoPaloBox2d /2, new Vector2(1.1f,2.2f/2+ altoPaloBox2d /2),0);
        fixture = body.createFixture(polygonShape, density);

        fixture.setFriction(0);
        fixture.setRestitution(0);
        fixture.setUserData(this);

        //pared izquierda
        PolygonShape polygonShape1 = new PolygonShape();
        final float density1 = 0.2f;
        polygonShape1.setAsBox(anchoPaloBox2d, altoPaloBox2d /2, new Vector2(-1.1f,2.2f/2+ altoPaloBox2d /2),0);
        fixture = body.createFixture(polygonShape1, density1);
        fixture.setFriction(0);
        fixture.setRestitution(0);
        fixture.setUserData(this);

        //fondo cañón
        PolygonShape polygonShape2 = new PolygonShape();
        final float density2 = 0.2f;
        polygonShape2.setAsBox(2.2f/2+anchoPaloBox2d, (1.1f+anchoPaloBox2d)/2, new Vector2(0,1.1f/2),0);
        fixture = body.createFixture(polygonShape2, density2);
        fixture.setFriction(0);
        fixture.setRestitution(0);
        fixture.setUserData(this);

        //soporte
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(1.1f+anchoPaloBox2d);
        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.restitution = 0.1f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 100;
        fixtureDef.friction = 0;
        fixtureDef.shape = circleShape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        //sensor cañon
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-1.1f, 1.1f+anchoPaloBox2d*2), new Vector2(1.1f, 1.1f+anchoPaloBox2d*2));
        fixtureDef.shape = feet;
        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData("sensorCanon");
    }

    @Override
    public void dispose() {
        // FIXME: Añadir código para eliminar este objeto
        textura.dispose();
    }
    public void gira(boolean sentidoHorario){
        if (sentidoHorario) {
            velocidadGiro = 30;
        } else {
            velocidadGiro = -30;
        }
    }
    public void detenGiro(){
        velocidadGiro = 0;
    }
    @Override
    public void act(float dt){
        angulo += dt*velocidadGiro;
        body.setTransform(body.getPosition(),angulo* MathUtils.degreesToRadians);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // FIXME: Aquí deberíamos dibujar el bloque a partir de un bitmap

        //anchoPaloBox2d,altoPaloBox2d
        //batch.draw(textura, body.getWorldCenter().x - anchoPaloBox2d, body.getWorldCenter().y - altoPaloBox2d, anchoPaloBox2d, altoPaloBox2d, anchoPaloBox2d * 2, altoPaloBox2d * 2, 1, 1, body.getAngle()*100, 0, 0, 1, 1, false, false);
    }
}
