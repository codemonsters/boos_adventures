package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.physics.box2d.World;

public abstract class ObjetoDinamico extends ObjetoDelJuego {

    public ObjetoDinamico() {
        super();
    }

    @Override
    public abstract void act(float dt);

}
