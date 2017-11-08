package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/***
 The playable world it:
 32 meters high
 18 meters wide

 But the lower quarter is reserved to allow touch controls without covering the scene and to show the HUD of the game

 So the playable area is located inside a U-shaped rigid body created using three fixtures (the ground and two walls):
 * The ground is a square (8 meters high and 18 wide)
 * Each wall is 24 meters high and 0.5 meters wide

 ***/

public class LimitesNivel extends ObjetoEstatico {

    private World world;

    public LimitesNivel(World world) {
        super(world);
        this.world = world;
        defineBody();
    }

    private void defineBody() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(0, 0);
        Body body = world.createBody(bdef);
        body.setUserData(this);

        PolygonShape polygonShape = new PolygonShape();
        final float floor_density = 10; // TODO: Review floor density and other properties

        // Floor
        polygonShape.setAsBox(18, 8);
        fixture = body.createFixture(polygonShape, floor_density);
        fixture.setUserData(this);

        // Left wall
        polygonShape.setAsBox(0.25f, 12, new Vector2(0.25f, 20), 0);
        fixture = body.createFixture(polygonShape, floor_density);
        fixture.setUserData(this);

        // Right wall
        polygonShape.setAsBox(0.25f, 12, new Vector2(17.75f, 20), 0);
        fixture = body.createFixture(polygonShape, floor_density);
        fixture.setUserData(this);

        // Top wall
        polygonShape.setAsBox(18, 0.25f, new Vector2(18, 31.50f), 0);
        fixture = body.createFixture(polygonShape, floor_density);
        fixture.setUserData(this);

        // Top world sensor (it is located 0.5m above the ceiling of the playableworld. When the player touches this sensor he wins and should get to a new level)
        EdgeShape goal = new EdgeShape();
        goal.set(new Vector2(-18, 32.5f), new Vector2(18, 32.5f));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = goal;
        fixtureDef.isSensor = true; // This is a sensor, it will not collide with other bodies of the world
        fixture = body.createFixture(fixtureDef); // Define "head" as the unique name of this fixture to help us to identify it during collisions
        fixture.setUserData("goal");

    }

}
