package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import es.codemonsters.boosadventures.game.objetosdeljuego.Bloque;

import es.codemonsters.boosadventures.game.objetosdeljuego.Meta;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;
import es.codemonsters.boosadventures.game.objetosdeljuego.SensoresLimitesMundo;

public class ContactListenerJuego implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof SensoresLimitesMundo || fixB.getUserData() instanceof SensoresLimitesMundo) {
            // Algún cuerpo ha llegado al límite del mundo
            Fixture otro = fixA.getUserData() instanceof SensoresLimitesMundo ? fixB : fixA;
            if (otro.getUserData() instanceof ObjetoJugador) {
                // El objeto jugador ha tocado el límite del mundo
                ((ObjetoJugador)otro.getUserData()).onLimitesMundoBeginContact();
            }
        } else if (fixA.getUserData() == "piesJugador" || fixB.getUserData() == "piesJugador") {
            // El sensor de los pies del jugador ha tocado con algo
            Fixture feet = fixA.getUserData() == "piesJugador" ? fixA : fixB;
            ((ObjetoJugador)feet.getBody().getUserData()).onFeetBeginContact();
        } else if (fixA.getUserData() instanceof ObjetoJugador || fixB.getUserData() instanceof ObjetoJugador) {
            // El cuerpo del jugador ha tocado con algo
            ObjetoJugador objetoJugador;
            Fixture otro;
            if (fixA.getUserData() instanceof ObjetoJugador) {
                objetoJugador = (ObjetoJugador) fixA.getUserData();
                otro = fixB;
            } else {
                objetoJugador = (ObjetoJugador) fixB.getUserData();
                otro = fixA;
            }
            if (otro.getUserData() instanceof Bloque){
                objetoJugador.onBloqueBeginContact((Bloque)otro.getUserData());
            } else if (otro.getUserData() instanceof Meta) {
                // Un jugador ha llegado a la meta
                objetoJugador.onMetaBeginContact();
            }
            //Gdx.app.debug("ContactListnerJuego", "El ObjetoJugador ha tocado algo");
        } else {
            Gdx.app.debug("ContactListnerJuego", "Algo a tocado con algo (" + fixA.getUserData() + ", " + fixB.getUserData() + ")");
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
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
