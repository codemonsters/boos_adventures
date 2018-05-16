package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public abstract class ObjetoDelJuego extends Actor implements Disposable {

    protected Fixture fixture;
    protected Body body;

    public ObjetoDelJuego() {
    }

    public Body getBody() {
        return body;
    }

    public abstract void definirCuerpo(World world);

    /* Descomentar para poder girar cualquier objeto!
    public void Rotate(int angulo){
        fixture.getBody().setTransform(fixture.getBody().getWorldCenter(), Utiles.gradosSexagesimalesARadianes(angulo));
    }*/

}
