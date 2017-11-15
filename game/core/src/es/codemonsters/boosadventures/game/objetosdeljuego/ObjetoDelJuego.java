package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public abstract class ObjetoDelJuego {

    protected Fixture fixture;

    public ObjetoDelJuego() {
    }

    public abstract void definirCuerpo(World world);
}
