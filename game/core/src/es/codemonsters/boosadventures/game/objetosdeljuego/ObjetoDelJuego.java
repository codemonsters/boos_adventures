package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public abstract class ObjetoDelJuego implements Disposable {

    protected Fixture fixture;

    public ObjetoDelJuego() {
    }

    public abstract void definirCuerpo(World world);

    @Override
    public abstract void dispose();
}
