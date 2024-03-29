package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;


public class Spawn extends ObjetoDelJuego {
    Texture textura;
    TextureRegion texturaRegion;
    private Body body;
    private  float angulo;
    private float xCentro, yCentro;
    private static final float RADIO_DEL_CUERPO = 1f;
    private static final float RADIO_DEL_SENSOR = 0.3f;
    float alpha = 0.2f;
    boolean transparentando = false;

    public Spawn(float xCentro, float yCentro) {
        super();
        textura = new Texture(Gdx.files.internal("Meta/meta.png"));
        texturaRegion = new TextureRegion(textura);
        this.xCentro = xCentro;
        this.yCentro = yCentro;
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

    }

    public Vector2 getPosicion(){
        return new Vector2(xCentro,yCentro);
    }

    @Override
    public void dispose() {
        textura.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //maneja el alpha y el angulo
        angulo -= Gdx.graphics.getDeltaTime()*50;
        if (transparentando){
            alpha += Gdx.graphics.getDeltaTime();
            if (alpha>1){
                alpha = 1;
                transparentando = false;
            }
        }
        else{
            alpha -= Gdx.graphics.getDeltaTime();
            if (alpha<0.2f){
                alpha = 0.2f;
                transparentando = true;
            }
        }
        Color c = batch.getColor();
        batch.setColor(c.r,c.g,c.b,alpha);
        batch.draw(texturaRegion,body.getPosition().x - RADIO_DEL_CUERPO, body.getPosition().y - RADIO_DEL_CUERPO, RADIO_DEL_CUERPO, RADIO_DEL_CUERPO, RADIO_DEL_CUERPO*2, RADIO_DEL_CUERPO*2, 1, 1, angulo);

        batch.setColor(c.r,c.g,c.b,1);
    }

}
