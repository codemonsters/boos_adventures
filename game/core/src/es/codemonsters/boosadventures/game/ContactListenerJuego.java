package es.codemonsters.boosadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import es.codemonsters.boosadventures.game.objetosdeljuego.Bloque;

import es.codemonsters.boosadventures.game.objetosdeljuego.Canon;
import es.codemonsters.boosadventures.game.objetosdeljuego.Meta;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;
import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoJugador;
import es.codemonsters.boosadventures.game.objetosdeljuego.Palanca;
import es.codemonsters.boosadventures.game.objetosdeljuego.SensoresLimitesMundo;
import es.codemonsters.boosadventures.game.pantallas.PantallaJuego;

public class ContactListenerJuego implements ContactListener {
    PantallaJuego pantallaJuego;
    public ContactListenerJuego(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
    }
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
            } else if (otro.getUserData().equals("sensorPalancaBoton")){
                pantallaJuego.dispararCanones();
            }
            //Gdx.app.debug("ContactListnerJuego", "El ObjetoJugador ha tocado algo");
        } else if (fixA.getUserData() instanceof Palanca || fixB.getUserData() instanceof Palanca) {
            Palanca palanca;
            Fixture otro;
            if (fixA.getUserData() instanceof Palanca) {
                palanca = (Palanca)fixA.getUserData();
                otro = fixB;
            } else {
                palanca = (Palanca)fixB.getUserData();
                otro = fixA;
            }
            if (otro.getUserData() == "sensorPalancaIzq") {
                // Giramos los cañones en sentido antihorario
                palanca.giraCanones(false);
            } else if (otro.getUserData() == "sensorPalancaDer") {
                // Giramos los cañones en sentido horario
                palanca.giraCanones(true);
            }
        } else {
            Gdx.app.debug("ContactListnerJuego", "Algo a tocado con algo (" + fixA.getUserData() + ", " + fixB.getUserData() + ")");
        }
        // TODO: Sería genial modificar esto para que el if del cañon sea un else if de lo anterior
        if (fixA.getUserData() instanceof UserDataBundle || fixB.getUserData() instanceof UserDataBundle) {
            UserDataBundle userDataBundle;
            ObjetoDelJuego otro;
            if (fixA.getUserData() instanceof UserDataBundle) {
                userDataBundle = (UserDataBundle) fixA.getUserData();
                otro = (ObjetoDelJuego)fixB.getUserData();
            } else {
                userDataBundle = (UserDataBundle) fixB.getUserData();
                otro = (ObjetoDelJuego)fixA.getUserData();
            }
            if (userDataBundle.texto.equals("sensorCanon")) {
                ((Canon)userDataBundle.objetoDelJuego).añadirObjetoDelJuego(otro);
            }
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
        else if (fixA.getUserData() instanceof Palanca || fixB.getUserData() instanceof Palanca){
            Palanca palanca;
            Fixture otro;
            if (fixA.getUserData() instanceof Palanca) {
                palanca = (Palanca)fixA.getUserData();
                otro = fixB;
            } else {
                palanca = (Palanca)fixB.getUserData();
                otro = fixA;
            }
            if (otro.getUserData() == "sensorPalancaIzq" || otro.getUserData() == "sensorPalancaDer") {
                // Detenemos el giro de los cañones
                palanca.detenerGiroCanones();
            }
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
        }

        if (fixA.getUserData() instanceof UserDataBundle || fixB.getUserData() instanceof UserDataBundle) {
            UserDataBundle userDataBundle;
            ObjetoDelJuego otro;
            if (fixA.getUserData() instanceof UserDataBundle) {
                userDataBundle = (UserDataBundle) fixA.getUserData();
                otro = (ObjetoDelJuego)fixB.getUserData();
            } else {
                userDataBundle = (UserDataBundle) fixB.getUserData();
                otro = (ObjetoDelJuego)fixA.getUserData();
            }
            if (userDataBundle.texto.equals("sensorCanon")) {
                ((Canon)userDataBundle.objetoDelJuego).quitarObjetoDelJuego(otro);
            }
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
