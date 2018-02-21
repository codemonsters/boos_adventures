package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import es.codemonsters.boosadventures.game.objetosdeljuego.Bloque;

import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;

public class ContactListenerJuego implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Array<Fixture> fixtures = new Array<Fixture>();
        Array<ObjetoDelJuego> objetosDelJuego = new Array<ObjetoDelJuego>();
        fixtures.add(contact.getFixtureA());
        fixtures.add(contact.getFixtureB());
        //Gdx.app.trace("ContactListenerJuego", "Comienza un contacto");
        // Comprobamos si son los pies de un jugador tocando algún cuerpo
        for (Fixture fixture : fixtures){
            if (fixture.getUserData().equals("piesJugador")) {
                Fixture feet = fixture;
                ((ObjetoJugador)feet.getBody().getUserData()).onFeetBeginContact();
                return;
            } else if (fixture.getUserData() == Bloque.class) {
                // FIXME: Este if nunca se cumple!
                objetosDelJuego.add((Bloque)fixture.getBody().getUserData());
            } else if (fixture.getUserData().equals("ObjetoJugador")) {
                objetosDelJuego.add((ObjetoJugador)fixture.getBody().getUserData());
            } else {
                Gdx.app.log("ContactListener", "beginContact(): Tipo de fixture ignorado: ->" + fixture.getUserData() + "<-");
            }
        }

        if (objetosDelJuego.get(0) instanceof ObjetoJugador || objetosDelJuego.get(1) instanceof ObjetoJugador) {
            // El ObjetoJugador ha tocado algo
            if (objetosDelJuego.get(0) instanceof ObjetoJugador) {
                if (objetosDelJuego.get(1) instanceof Bloque) {
                    ((ObjetoJugador) objetosDelJuego.get(0)).onBloqueBeginContact((Bloque)objetosDelJuego.get(1));
                }
            } else {
                if (objetosDelJuego.get(0) instanceof Bloque) {
                    ((ObjetoJugador) objetosDelJuego.get(1)).onBloqueBeginContact((Bloque)objetosDelJuego.get(0));
                }
            }
        }




        /*
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "piesJugador" || fixB.getUserData() == "piesJugador") {
            Fixture feet = fixA.getUserData() == "piesJugador" ? fixA : fixB;
            ((ObjetoJugador)feet.getBody().getUserData()).onFeetBeginContact();
        }
        if (fixA.getUserData() == "ObjetoJugador" || fixB.getUserData() == "ObjetoJugador" || fixA.getUserData() == "piesJugador" || fixB.getUserData() == "piesJugador") {
            Fixture jugador;
            Fixture otro;
            if (fixA.getUserData() == "ObjetoJugador") {
                jugador = fixA;
                otro = fixB;
            } else {
                jugador = fixB;
                otro = fixA;
            }
            Gdx.app.log("ContactListenerJuego", "jasdasdadasdadasd!");
            if (otro.getUserData()=="Muerte"){
                Gdx.app.log("ContactListenerJuego", "jugador ha comenzado contacto con algo!");
            }
        }
        //Gdx.app.debug("ContactListenerJuego", "fixA.getUserData() == " + fixA.getUserData() + ", fixB.getUserData() == " + fixB.getUserData());
        */
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
