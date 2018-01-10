package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class ObjetoJugador extends ObjetoDinamico {

    private static final float IMPULSO_MOVIMIENTO = 8000; // Impulso aplicado al jugador cuando se quiere mover (en newtons por segundo)
    private static final float FUERZA_SALTO = 7200; // Impulso aplicado al jugador para que salte (en newtons)

    private float xCentro, yCentro;
    public Body body;   // FIXME: Poner un getter? Revisar esto
    private boolean presionandoDerecha, presionandoIzquierda, presionandoArriba, presionandoAbajo, presionandoBoton1 = false;
    private boolean estaApoyado = false;
    private boolean saltarEnSiguienteUpdate = false;
    private boolean yaEstaSaltando = false;
    float deltaTime = 0;    // FIXME: Renombrar a algo más significativo
    int frame = 0;
    private Array<Texture> texturas;
    private Texture texturaActual;


    public ObjetoJugador(float xCentro, float yCentro) {
        super();
        this.xCentro = xCentro;
        this.yCentro = yCentro;

        texturas = new Array<Texture>();
        texturas.add(new Texture(Gdx.files.internal("Boo/Idle/0.png")));
        texturas.add(new Texture(Gdx.files.internal("Boo/Idle/1.png")));
        texturaActual = texturas.get(0);    // FIXME: Eliminar esto y sobreescribir el método act() de la clase Actor (posiblemente renombrar el método update() de esta clase a act()
    }

    public void definirCuerpo(World world) {
        // El jugador es un cuerpo circular de 1,5 metros de diámetro

        // Body
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        //bdef.position.set(9, 8.5f);
        bdef.position.set(xCentro, yCentro);
        body = world.createBody(bdef);
        body.setFixedRotation(true);
        body.setUserData(this);

        // Fixture principal (círculo de 1,5m de diámetro)
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.75f);
        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.restitution = 0.1f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 10;
        fixtureDef.friction = 0.5f;
        fixtureDef.shape = circleShape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        /*
        // Sensor de la cabeza
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-0.50f, 0.75f), new Vector2(0.50f, 0.75f));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true; // Es un sensor, no colisionará con otros cuerpos del mundo
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData("cabezaJugador"); // Definimos un nombre único en el fixture para identificarlo con facilidad cuando contacte con otros cuerpos
        */

        // Sensor de los pies
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-0.50f, -0.75f), new Vector2(0.50f, -0.75f));
        fixtureDef.shape = feet;
        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData("piesJugador");
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texturaActual,0,0);
    }

    @Override
    public void dispose() {
        for(Texture textura : texturas) {
            textura.dispose();
        }
    }

    @Override
    public void update(float dt) {
        if (estaApoyado) {
            if (saltarEnSiguienteUpdate && !yaEstaSaltando) {
                yaEstaSaltando = true;
                Gdx.app.debug("ObjetoJugador", "¡Saltar!");
                saltarEnSiguienteUpdate = false;
                if (presionandoDerecha) {
                    // Salto diagonal derecha
                    body.applyForceToCenter(new Vector2(body.getLinearVelocity().x, FUERZA_SALTO), true);
                } else if (presionandoIzquierda) {
                    // Salto diagonal izquierda
                    body.applyForceToCenter(new Vector2(body.getLinearVelocity().x, FUERZA_SALTO), true);
                } else {
                    // Salto arriba
                    body.applyForceToCenter(new Vector2(0, FUERZA_SALTO), true);
                }
                saltarEnSiguienteUpdate = false;
            } else {
                if (presionandoIzquierda) {
                    // Desplazamiento a la izquierda
                    body.applyForceToCenter(new Vector2(-1 * IMPULSO_MOVIMIENTO * dt, 0), true);
                }
                if (presionandoDerecha) {
                    // Desplazamiento a la derecha
                    body.applyForceToCenter(new Vector2(+1 * IMPULSO_MOVIMIENTO * dt, 0), true);
                }
            }
        }
        // Actualizar el sprite
        deltaTime += dt;
        if (deltaTime>0.5f){
            deltaTime =0;
            if (frame == 0){
                //sprite = new Sprite(new Texture(Gdx.files.internal("Boo/Idle/0.png")),100,100);
            }
            else{
                //sprite = new Sprite(new Texture(Gdx.files.internal("Boo/Idle/1.png")),100,100);
                frame = -1;
            }
            frame ++;
            texturaActual = texturas.get(frame);
        }

    }

    public void setPresionandoDerecha(boolean presionandoDerecha) {
        this.presionandoDerecha = presionandoDerecha;
    }

    public void setPresionandoIzquierda(boolean presionandoIzquierda) {
        this.presionandoIzquierda = presionandoIzquierda;
    }

    public void setPresionandoArriba(boolean presionandoArriba) {
        this.presionandoArriba = presionandoArriba;
    }

    public void setPresionandoAbajo(boolean presionandoAbajo) {
        this.presionandoAbajo = presionandoAbajo;
    }

    public void setPresionandoBoton1(boolean presionandoBoton1) {
        Gdx.app.debug("ObjetoJugador", "BOTON PRESIONADO");
        this.presionandoBoton1 = presionandoBoton1;
        if (!yaEstaSaltando && presionandoBoton1 == true) {
            saltarEnSiguienteUpdate = true;
        }
    }

    public void onFeetBeginContact() {
        //Gdx.app.debug("ObjetoJugador", "Pies apoyados");
        estaApoyado = true;
        yaEstaSaltando = false;
    }

    public void onFeetEndContact() {
        //Gdx.app.debug("ObjetoJugador", "Pies sin apoyo");
        estaApoyado = false;
    }
}
