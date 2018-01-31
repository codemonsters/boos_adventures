package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;

public class ContactListenerJuego implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        //Gdx.app.trace("ContactListenerJuego", "Comienza un contacto");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        // Comprobamos si son los pies de un jugador tocando algún cuerpo
        if (fixA.getUserData() == "piesJugador" || fixB.getUserData() == "piesJugador") {
            Fixture feet = fixA.getUserData() == "piesJugador" ? fixA : fixB;
            ((ObjetoJugador)feet.getBody().getUserData()).onFeetBeginContact();
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Gdx.app.debug("ContactListener", "FIN CONTACTO (fixA = " + fixA.getUserData() + " ; fixB = " + fixB.getUserData());
        // Comprobamos si son los pies de un jugador los que dejan de tocar algún cuerpo
        if (fixA.getUserData() == "piesJugador" || fixB.getUserData() == "piesJugador") {
            Fixture feet = fixA.getUserData() == "piesJugador" ? fixA : fixB;
            ((ObjetoJugador)feet.getBody().getUserData()).onFeetEndContact();
        }

        /*
        // Otro código genérico de ejemplo: Comprobamos si un enemigo ha contactado con cualquier tipo de cuerpo estático
        if (Enemy.class.isAssignableFrom(fixA.getUserData().getClass())) {
            ((Enemy)fixA.getUserData()).afterCollisionWithStaticObject();
        }
        if (Enemy.class.isAssignableFrom(fixB.getUserData().getClass())) {
            ((Enemy)fixB.getUserData()).afterCollisionWithStaticObject();
        }
        */
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO: Método autogenerado, revisar
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO: Método autogenerado, revisar
    }

}
