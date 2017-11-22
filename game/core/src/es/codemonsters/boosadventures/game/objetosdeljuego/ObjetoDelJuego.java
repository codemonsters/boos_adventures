package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import es.codemonsters.boosadventures.game.Utiles;

public abstract class ObjetoDelJuego {

    protected Fixture fixture;

    public ObjetoDelJuego() {
    }

    public abstract void definirCuerpo(World world);
    /* Descomentar para poder girar cualquier objeto!
    public void Rotate(int angulo){
        fixture.getBody().setTransform(fixture.getBody().getWorldCenter(), Utiles.gradosSexagesimalesARadianes(angulo));
    }*/
}
