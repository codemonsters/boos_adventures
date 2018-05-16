package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Piedra extends ObjetoDelJuego{
    float radio,xCentro,yCentro;
    private Texture textura;
    public Piedra(float radio, float xCentro, float yCentro) {
        super();
        this.radio = radio;
        this.xCentro = xCentro;
        this.yCentro = yCentro;
        textura = new Texture(Gdx.files.internal("Piedra/0.png"));
    }

    @Override
    public void definirCuerpo(World world) {
        // El jugador es un cuerpo circular de 1,5 metros de diámetro

        // Body
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        //bdef.position.set(9, 8.5f);
        bdef.position.set(xCentro, yCentro);
        body = world.createBody(bdef);
        body.setFixedRotation(false);
        body.setUserData(this);

        // Fixture principal (círculo de 1,5m de diámetro)
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radio);
        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.restitution = 0.1f;
        fixtureDef.restitution = 0.2f;
        fixtureDef.density = 50;
        fixtureDef.friction = 0.5f;
        fixtureDef.shape = circleShape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    @Override
    public void dispose() {
        textura.dispose();
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textura,body.getPosition().x - radio,body.getPosition().y - radio,radio,radio,radio*2,radio*2,1, 1, body.getAngle() * MathUtils.radiansToDegrees,0,0,textura.getWidth(),textura.getHeight(),false,false);
    }
}
