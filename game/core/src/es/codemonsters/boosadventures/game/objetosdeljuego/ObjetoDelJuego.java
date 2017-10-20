package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public abstract class ObjetoDelJuego {

    private World world;
    protected Fixture fixture;

    public ObjetoDelJuego(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

}
