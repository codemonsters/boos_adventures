package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import es.codemonsters.boosadventures.game.pantallas.PantallaJuego;


public class Meta extends ObjetoEstatico {
    Array<Texture> texturas;
    private Body body;
    private  float index;
    private float xCentro, yCentro;
    private static final float RADIO_DEL_CUERPO = 1f;

    public Meta(float xCentro, float yCentro) {
        super();
        texturas = new Array<Texture>();
        this.xCentro = xCentro;
        this.yCentro = yCentro;
        for (int i =1; i<= 15; i++) {
            texturas.add(new Texture(Gdx.files.internal("Meta/meta"+i+".png")));
        }
    }

    @Override
    public void definirCuerpo(World world) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        //bdef.position.set(9, 8.5f);
        bdef.position.set(xCentro, yCentro);
        body = world.createBody(bdef);
        body.setFixedRotation(true);
        body.setUserData(this);

        // Fixture principal (círculo de 1,5m de diámetro)
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(RADIO_DEL_CUERPO);
        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.restitution = 0.1f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 10;
        fixtureDef.friction = 0.5f;
        fixtureDef.isSensor = true;
        fixtureDef.shape = circleShape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    @Override
    public void dispose() {
        for (Texture texture : texturas)
            texture.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        index += Gdx.graphics.getDeltaTime()*25;
        if (index>14) {
            index = 0;
        }
        batch.draw(texturas.get((int)Math.round(index)),body.getPosition().x - RADIO_DEL_CUERPO,body.getPosition().y - RADIO_DEL_CUERPO,RADIO_DEL_CUERPO*2,RADIO_DEL_CUERPO*2);

    }

}
