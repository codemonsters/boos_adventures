package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import es.codemonsters.boosadventures.game.pantallas.PantallaJuego;

public class SensoresLimitesMundo extends ObjetoEstatico {
    private static final float MARGEN = 10;
    public SensoresLimitesMundo() {
        super();
    }

    @Override
    public void definirCuerpo(World world) {
        // TODO: Sustituir las cuatro líneas por un único polígono
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(0, 0);
        Body body = world.createBody(bdef);
        body.setUserData(this);

        // Sensores
        // Pared izquierda
        creaSensor(-MARGEN, -MARGEN, -MARGEN, PantallaJuego.ALTO_DEL_MUNDO * 2 + MARGEN, body);
        // Pared derecha
        creaSensor(PantallaJuego.ANCHO_DEL_MUNDO + MARGEN, -MARGEN, PantallaJuego.ANCHO_DEL_MUNDO + MARGEN,  PantallaJuego.ALTO_DEL_MUNDO * 2 + MARGEN, body);
        // Suelo
        creaSensor(-MARGEN, -MARGEN, PantallaJuego.ANCHO_DEL_MUNDO + MARGEN, -MARGEN, body);
        // Techo
        creaSensor(-MARGEN, PantallaJuego.ANCHO_DEL_MUNDO + MARGEN, PantallaJuego.ANCHO_DEL_MUNDO + MARGEN, PantallaJuego.ANCHO_DEL_MUNDO + MARGEN, body);
    }

    private void creaSensor(float x1, float y1, float x2, float y2, Body body) {
        EdgeShape linea = new EdgeShape();
        linea.set(new Vector2(x1, y1),new Vector2(x2, y2));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = linea;
        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    @Override
    public void dispose() {
        // FIXME: Añadir código para eliminar este objeto
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // FIXME: Aquí deberíamos dibujar los límites del nivel a partir de un bitmap
    }

}
