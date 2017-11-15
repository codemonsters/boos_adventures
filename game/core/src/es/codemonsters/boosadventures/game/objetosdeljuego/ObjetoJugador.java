package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class ObjetoJugador extends ObjetoDinamico {

    private Body body;
    // FIXME: Adaptar la clase para que todo se defina mendiante el mismo sistema (tanto lo expresado para box2d como para las texturas)
    public ObjetoJugador() {
        super();
    }
    // Defines the physical body of the player
    public void definirCuerpo(World world) {
        // Body
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(9, 8.5f);
        body = world.createBody(bdef);
        body.setFixedRotation(true);
        body.setUserData(this);

        // Main boundary fixture (1,5m diameter circle)
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.75f);
        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.restitution = 0.1f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 10;
        fixtureDef.friction = 0.5f;
        fixtureDef.shape = circleShape;
        fixture = body.createFixture(fixtureDef);  // TODO: Review player density and other proterties
        fixture.setUserData(this);

        // Head sensor
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-0.50f, 0.75f), new Vector2(0.50f, 0.75f));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true; // This is a sensor, it will not collide with other bodies of the world
        fixture = body.createFixture(fixtureDef); // Define "head" as the unique name of this fixture to help us to identify it during collisions
        fixture.setUserData("head");

        // Feet sensor
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-0.50f, -0.75f), new Vector2(0.50f, -0.75f));
        fixtureDef.shape = feet;
        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData("feet");
    }

    @Override
    public void update(float dt) {
        // TODO: Implementar
    }

}
